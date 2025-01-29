import java.util.List;

public class ProductSearch {


    public static Product searchProduct(List<Product> productList, String productName) {

        productList.sort((p1, p2) -> p1.compareToIgnoreCase(p2.name));

        int left = 0;
        int right = productList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = productList.get(mid);
            int comparison = midProduct.compareToIgnoreCase(productName);

            if (comparison == 0) {
                return midProduct;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}

