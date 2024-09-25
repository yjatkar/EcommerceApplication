package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import in.yashjatkar.ecommerce_project.Model.Category;
import in.yashjatkar.ecommerce_project.Model.Product;
import in.yashjatkar.ecommerce_project.repository.CategoryRepository;
import in.yashjatkar.ecommerce_project.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private RestTemplate restTemplate;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository,
                              RestTemplate restTemplate,
                              CategoryRepository categoryRepository
                              )
    {
        this.productRepository = productRepository;
        this.restTemplate=restTemplate;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Product product= productRepository.findProductById(id);
        if(product==null)
        {
            throw new  ProductNotFoundException("product with id "+id+" is not present");
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> product=productRepository.findAll();
        return product;

//        or
//           return productRepository.findAll();
    }

    @Override
    public Product CreateProduct(String title, Double price, String description, String image, String category) {
        Product product=new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);

        //check if category already present
        Category categoryFromDatabase=categoryRepository.findByTitle(category);

//        --------------we are using persist in Product table so even if category not present
//                no need to save seperately it will be done by cascade.Persist-----------
        if(categoryFromDatabase==null)
        {
            Category newCategory=new Category();
            newCategory.setTitle(category);
//            categoryFromDatabase=categoryRepository.save(newCategory);//if without cascade.Persist
//            use this above line to save Category first
            categoryFromDatabase=newCategory;//no need to save category it is done by persist
        }
        product.setCategory(categoryFromDatabase);
        Product savedProduct= productRepository.save(product);

        return savedProduct;


    }

    @Override
    public List<String> getAllCategory() {
        List<Category> categories = categoryRepository.findAll(); // Fetch all categories
        List<String> categoryNames = new ArrayList<>(); // Create a new list to hold names

        for (Category category : categories) {
            categoryNames.add(category.getTitle()); // Add each name to the list
        }

        return categoryNames; // Return the list of names
    }

    @Override
    public List<Product> getAllProductsForCategory(String title) {
//        ------------------(1)METHOD-------------------
            Category cat1=categoryRepository.findByTitle(title);
            List<Product> product=productRepository.findByCategory(cat1);
//        ------------------(2)METHOD-------------------
//        List<Product> product=productRepository.findByCategoryTitle(title);
        return product;

    }

    @Override
    public Product updateProduct(String title, Double price, String description, String category, String image, Long id) {
       Product product=productRepository.findProductById(id);
       if(title!=null){
           product.setTitle(title);
       }
        if(price!=null){product.setPrice(price);}

        if(description!=null){product.setDescription(description); }

        if(image!=null){product.setImage(image);}

        Category productCategory=new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);
        return product;

    }

    @Override
    public Product replaceProduct(Long id,
                                  String title,
                                  Double price,
                                  String description,
                                  String category,
                                  String image)
            throws ProductNotFoundException
    {
        Product product=productRepository.findProductById(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        Category productCategory=new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);
        return product;
//        return null;
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }

        productRepository.deleteById(id);
        return product;
    }
}
