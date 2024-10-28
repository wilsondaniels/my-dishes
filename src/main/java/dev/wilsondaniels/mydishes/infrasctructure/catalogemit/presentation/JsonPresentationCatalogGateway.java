package dev.wilsondaniels.mydishes.infrasctructure.catalogemit.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dev.wilsondaniels.mydishes.domain.catalogemit.PresentationCatalogGateway;
import dev.wilsondaniels.mydishes.domain.category.Category;
import dev.wilsondaniels.mydishes.domain.exception.DomainException;
import dev.wilsondaniels.mydishes.domain.product.Product;
import dev.wilsondaniels.mydishes.domain.validation.Error;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class JsonPresentationCatalogGateway implements PresentationCatalogGateway<String> {
    @Override
    public String create(String anOwnerId, Map<Category, Set<Product>> catalog) {

        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> onwerElement = new HashMap<>();
        onwerElement.put("owner", anOwnerId);

        final ArrayNode categoryArray = mapper.createArrayNode();
        for (final Category c : catalog.keySet()) {

            final ObjectNode itemCategory = mapper.createObjectNode();
            itemCategory.put("category_title", c.getTitle());
            itemCategory.put("category_description", c.getDescription());

            final ArrayNode itensArray = mapper.createArrayNode();
            final Set<Product> products = catalog.get(c);
            for (final Product p : products) {
                final ObjectNode itemProduct = mapper.createObjectNode();
                itemProduct.put("title", p.getTitle());
                itemProduct.put("description", p.getTitle());
                itemProduct.put("price", p.getPrice());
                itensArray.add(itemProduct);
            }

            itemCategory.put("itens", itensArray);
            categoryArray.add(itemCategory);
        }
        onwerElement.put("catalog", categoryArray);

        try {
            // return mapper.writeValueAsString(onwerElement);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(onwerElement);
        } catch (JsonProcessingException e) {
            throw DomainException.with(new Error("Error creating catalog file."));
        }
    }
}
