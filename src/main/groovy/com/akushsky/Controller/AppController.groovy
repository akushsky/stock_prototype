package com.akushsky.Controller

import com.akushsky.Entity.ExternProvider
import com.akushsky.Entity.Product
import com.akushsky.Entity.ProductType
import com.akushsky.Entity.ProductTypeEnum
import com.akushsky.Parser.DataFeedParser
import com.akushsky.Parser.FragrancenetDataFeedParser
import com.akushsky.Repository.ExternProviderRepository
import com.akushsky.Repository.ProductRepository
import com.akushsky.Repository.ProductTypeRepository
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper
import org.postgresql.util.PSQLException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.annotation.PostConstruct
import javax.xml.bind.DatatypeConverter

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

    @Autowired
    FragrancenetDataFeedParser dataFeedParser;

    /**
     * This method will list all existing products.
     */
    @RequestMapping(value = [ "/" ], method = RequestMethod.GET)
    public String listProducts(ModelMap model) {

        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "productslist";
    }

    /**
     * Products report group by type
     */
    @RequestMapping(value = [ "/report" ], method = RequestMethod.GET)
    public String reportProducts(ModelMap model) {

        Iterable<Product> products = productRepository.findAll();
        Map<ProductTypeEnum, List<Product>> report = products.groupBy { it.productType.type }
        model.addAttribute("products", report);
        return "productsreport";
    }

    /*
    * This method will list all existing products.
    */
    @RequestMapping(value = [ "/producttype" ], method = RequestMethod.GET)
    public String listProductTypes(ModelMap model) {
        List<ProductType> products = productTypeRepository.findAll();
        model.addAttribute("products", products);
        return "producttypeslist";
    }

    @RequestMapping(value = [ "/update-product-types" ], method = RequestMethod.GET)
    public String updateProductTypes(ModelMap model) {
        Iterable<ExternProvider> externProviderList = externProviderRepository.findAll();

        for (ExternProvider externProvider: externProviderList) {

            // TODO: Rewrite with login password in DB, now hardcode

            HttpURLConnection connection = (HttpURLConnection) new URL("https://www.fragrancenet.com/wsdata/datafeed2.txt").openConnection();
            String encoded = DatatypeConverter.printBase64Binary("fnetdatafeed:datafeed".getBytes());
            connection.setRequestProperty("Authorization", "Basic "+encoded);

            List<ProductType> productTypeList = dataFeedParser.parse(connection.getInputStream());
            productTypeList.each {
                it.externProviders = new ArrayList<>();
                it.externProviders.add(externProvider);
            }

            try {
                productTypeRepository.save(productTypeList);
            } catch (PSQLException ex) {
                ex.printStackTrace()
            }
        }

        Iterable<ProductType> products = productTypeRepository.findAll();
        model.addAttribute("products", products);
        return "producttypeslist";
    }

    @RequestMapping(value = [ "/delete-product-types" ], method = RequestMethod.GET)
    public String deleteProductTypes(ModelMap model) {
        productTypeRepository.deleteAllExtern()

        Iterable<ProductType> products = productTypeRepository.findAll();
        model.addAttribute("products", products);
        return "producttypeslist";
    }
}
