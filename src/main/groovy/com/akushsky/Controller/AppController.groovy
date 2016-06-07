package com.akushsky.Controller

import com.akushsky.Entity.ExternProvider
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

import javax.annotation.PostConstruct

import static org.junit.Assert.assertNotEquals
import static org.junit.Assert.assertNotEquals
import static org.junit.Assert.assertNotEquals
import static org.junit.Assert.assertNotEquals
import static org.junit.Assert.assertNotEquals

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

    @PostConstruct
    postConstruct() {
        externProviderRepository.deleteAll()
        productTypeRepository.deleteAll()
        productRepository.deleteAll()

        ExternProvider externProvider = new ExternProvider();
        externProvider.name = "fragrancenet"
        externProvider.url = "https://fnetdatafeed:datafeed@www.fragrancenet.com/wsdata/datafeed2.txt"

        ExternProvider providerResult = externProviderRepository.save(externProvider)

        assertNotEquals(providerResult.id, 0)

        ProductType productTypeChanel5 = new ProductType()
        productTypeChanel5.name = "Chanel #5"
        productTypeChanel5.type = ProductTypeEnum.BULK;

        ProductType result = productTypeRepository.save(productTypeChanel5)

        assertNotEquals(result.id, 0)

        Product product2 = new Product()
        product2.productType = result
        product2.bulk = 0
        product2.stockCount = 100
        product2.stockCountReserved = 0

        Product productResult = productRepository.save(product2)

        assertNotEquals(productResult.id, 0)

        ProductType productTypeChanel18 = new ProductType()
        productTypeChanel18.name = "Chanel #18"

        productTypeChanel18.type = ProductTypeEnum.FULL;
        if (productTypeChanel18.externProviders == null) {
            productTypeChanel18.externProviders = new HashSet<ExternProvider>()
        }
        productTypeChanel18.externProviders.add(providerResult)

        result = productTypeRepository.save(productTypeChanel18)

        assertNotEquals(result.id, 0)

        Product product1 = new Product()
        product1.productType = result
        product1.bulk = 180
        product1.stockCount = 10
        product1.stockCountReserved = 0

        productResult = productRepository.save(product1)

        assertNotEquals(productResult.id, 0)
    }

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
