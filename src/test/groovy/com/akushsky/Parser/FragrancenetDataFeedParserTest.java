package com.akushsky.Parser;

import com.akushsky.Entity.ProductType;
import com.akushsky.Entity.ProductTypeEnum;
import com.akushsky.Parser.FragrancenetDataFeedParser;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by akushsky on 6/8/16.
 */
public class FragrancenetDataFeedParserTest {

    FragrancenetDataFeedParser dataFeedParser;
    File fragrancenetTestDataFeed;

    @Before
    public void setUp() throws Exception {
        dataFeedParser = new FragrancenetDataFeedParser();
        fragrancenetTestDataFeed = new File(getClass().getClassLoader().getResource("fragrancenet_test_data_feed").getFile());
    }

    @Test
    public void testParse() throws Exception {
        assertNull(dataFeedParser.parse(null));

        InputStream inputStream = new FileInputStream(fragrancenetTestDataFeed);

        List<ProductType> result = dataFeedParser.parse(inputStream);

        assertNotNull(result);

        ProductType testType = result.get(0);

        assertEquals(testType.getName(), "Everlasting Foundation+ SPF15 - # 110 Honey --30ml/1.1oz");
        assertEquals(testType.getType(), ProductTypeEnum.FULL);
    }
}