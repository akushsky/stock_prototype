package com.akushsky.Controller

import com.akushsky.Entity.Product
import com.akushsky.Entity.ProductType
import com.akushsky.Entity.ProductTypeEnum
import com.akushsky.Repository.ExternProviderRepository
import com.akushsky.Repository.ProductRepository
import com.akushsky.Repository.ProductTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import java.util.stream.Collectors

/**
 * Created by akushsky on 6/6/16.
 */
@Controller
@RequestMapping("/")
class AppController {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ExternProviderRepository externProviderRepository;

    /**
     * This method will list all existing products.
     */
    @RequestMapping(value = [ "/", "/list" ], method = RequestMethod.GET)
    public String listProducts(ModelMap model) {

        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "productslist";
    }

    @RequestMapping(value = [ "/report" ], method = RequestMethod.GET)
    public String reportProducts(ModelMap model) {

        List<Product> products = productRepository.findAll();
        Map<ProductTypeEnum, Product> report = products.groupBy { it.productType.type }
        model.addAttribute("products", report);
        return "productsreport";
    }
}
