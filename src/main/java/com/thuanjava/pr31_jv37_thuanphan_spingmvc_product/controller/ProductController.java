/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.controller;

import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.CategoryEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ImageEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.OrderEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.entities.ProductEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.CartInfo;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.Order;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.models.ProductPlusEntity;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service.CategoryService;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service.MailService;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service.OrderDetailService;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service.OrderService;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.service.ProductService;
import com.thuanjava.pr31_jv37_thuanphan_spingmvc_product.utils.Utils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author NO1
 */
@Controller
public class ProductController {

    @Autowired
    private MailService mailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model,
            @RequestParam(name = "message", required = false) String message) {
        model.addAttribute("message", message);
        model.addAttribute("products", productService.getProducts());
        return "home";
    }

    @RequestMapping("/create-product")
    public String createProduct(Model model) {
        model.addAttribute("productPlus", new ProductPlusEntity());
//        model.addAttribute("product", new ProductEntity());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("action", "add");
        return "createProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createProduct(Model model, @ModelAttribute ProductPlusEntity productPlus, HttpServletRequest request) {
        ProductEntity product = productPlus.getProduct();
        System.out.println(product.getName() + "--------");
        MultipartFile file = productPlus.getFile();
        ImageEntity image = new ImageEntity();
        image.setName(file.getOriginalFilename());
        List<ImageEntity> images = new ArrayList<>();
        images.add(image);
        product.setImages(images);

        String messageUpload = "";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Creating the directory to store file
                ServletContext context = request.getServletContext();
                String pathUrl = context.getRealPath("/images");

                int index = pathUrl.indexOf("target");
                String pathFolder = pathUrl.substring(0, index) + "src\\main\\webapp\\resources\\images\\";
                Path path = Paths.get(pathFolder + file.getOriginalFilename());
                Files.write(path, bytes);

                // sau khi upload file xong lấy file name ra để insert vào database
                String name = file.getOriginalFilename();

                messageUpload = "You successfully uploaded file=" + name;
            } catch (Exception e) {
                messageUpload = "You failed to upload " + " => " + e.getMessage();
            }
        } else {
            messageUpload = "You failed to upload "
                    + " because the file was empty.";
        }
        productService.save(product, "add");
        return "redirect:/home?message=" + messageUpload;
    }

    @RequestMapping("/update-product/{productId}")
    public String updateProduct(Model model, @PathVariable("productId") int id) {
        ProductEntity product = productService.getProduct(id);
        if (product.getId() > 0) {
            model.addAttribute("action", "update");
            model.addAttribute("productUpdate", product);
            model.addAttribute("categories", categoryService.getCategories());
            return "createProduct";
        } else {
            return "redirect:/home?type=error&message=Not found book id: " + id;
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(Model model, @ModelAttribute ProductPlusEntity productPlus) {
        ProductEntity productEntity = productPlus.getProduct();
        productService.save(productEntity, "update");
        return "redirect:/home";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(Model model,
            HttpServletRequest request) {
        model.addAttribute("CartInfo", Utils.getCartInSession(request));
        model.addAttribute("TotalPrice", Utils.getTotalPrice(request));
        return "cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String UpdateQuantity(Model model,
            HttpServletRequest request,
            @ModelAttribute("CartInfoForm") CartInfo cartInfoForm) {

        Utils.updateQuantityCart(request, cartInfoForm);
        model.addAttribute("CartInfo", Utils.getCartInSession(request));
        return "redirect:/cart";
    }

    @RequestMapping("/add-to-cart/{productId}")
    public String addCart(Model model,
            @PathVariable("productId") int id,
            HttpServletRequest request) {
        ProductEntity product = productService.getProduct(id);
        Utils.addCartItemInSession(request, product);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/removeCartItem/{productId}")
    public String removeCart(Model model,
            HttpServletRequest request,
            @PathVariable("productId") int id) {
        ProductEntity product = productService.getProduct(id);
        Utils.deteleCartItem(request, product);
        System.out.println("OKOKOKKKKKKKKKKKKK");
        return "redirect:/cart";
    }

    @RequestMapping(value = "/orderSubmit", method = RequestMethod.GET)
    public String getOrder(Model model,
            HttpServletRequest request) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        if (cartInfo.getCartItems().isEmpty()) {
            return "redirect:/home?type=error&message=No item in cart. Please buy some Product";
        }
        Order order = new Order();
        model.addAttribute("Order", order);
        return "orderSubmit";
    }

    @RequestMapping(value = "/orderSubmit", method = RequestMethod.POST)
    public String submitOrder(Model model,
            @ModelAttribute("Order") Order order,
            HttpServletRequest request) throws MessagingException {
        CartInfo cartInfo = Utils.getCartInSession(request);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName(order.getName());
        orderEntity.setAddress(order.getAddress());
        orderEntity.setPhone(order.getPhone());
        orderEntity.setOrderDate(order.getOrderDate());
        orderService.save(orderEntity);
        orderDetailService.saveList(cartInfo.getCartItems(), orderEntity.getId());
        Utils.orderSubmit(request, order);
        String subject = "Order Confirmation #" + orderEntity.getId();
        mailService.sentHtmlMail("luongthuan93@gmail.com", subject, cartInfo);
//        mailService.sendMail("luongthuan93@gmail.com", "Order Confirmation", "Success Sent Email");
        Utils.removeCartInSession(request);
        return "redirect:/home?type=success&message=Order Success. Email Send";
    }
}
