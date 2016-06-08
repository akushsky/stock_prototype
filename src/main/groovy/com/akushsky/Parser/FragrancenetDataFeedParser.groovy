package com.akushsky.Parser

import com.akushsky.Entity.ProductType
import com.akushsky.Entity.ProductTypeEnum
import org.springframework.stereotype.Service

/**
 * Concrete data feed parser
 */
@Service
class FragrancenetDataFeedParser implements DataFeedParser<ProductType> {
    @Override
    List<ProductType> parse(InputStream inputStream) {
        if (inputStream == null) {
            return null
        }

        List<ProductType> result = new ArrayList<>()
        Scanner scanner = new Scanner(inputStream);
        // We must pass first line with heading
        if (scanner.hasNextLine()) scanner.nextLine()

        try {
            while (scanner.hasNextLine()) {
                // Split each line by tab separator
                String[] variables = scanner.nextLine().split("\\t")
                // Create new product type
                ProductType productType = new ProductType();
                // Name stores in 6 place
                productType.name = variables[3];
                // Type check by 8ml attribute (format strange)
                // TODO: Need add real parser for bulk
                productType.type = variables[6].contains("--8ml") ? ProductTypeEnum.FACT : ProductTypeEnum.FULL

                result.add(productType)
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        result
    }
}
