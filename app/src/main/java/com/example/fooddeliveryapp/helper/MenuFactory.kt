package com.example.fooddeliveryapp.helper

import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.model.Category
import com.example.fooddeliveryapp.model.Product
import java.util.UUID

enum class CategoryType(
    val categoryId:Long,
    val categoryName: String,
    val imageId: Int,
    val categoryOrdinal: Int
) {
    PIZZA(10001, "Pizza", R.drawable.cat_1, 1),
    BURGERS(10002, "Burger", R.drawable.cat_2, 2),
    HOTDOGS(10003, "Hot dog", R.drawable.cat_3, 3),
    DRINKS(10004, "Soda", R.drawable.cat_4, 4),
    DONUTS(10005, "Donut", R.drawable.cat_5, 5);

    fun getProduct(
        productName: String,
        productPrice: Double,
        rating: Double,
        waitingTime: Long,
        calories:Int,
        description:String,
        imageUrl:String
    ):  Product {
        return Product(
            id = UUID.randomUUID().toString(),
            name = productName,
            price = productPrice,
            rating = rating,
            waitingTime = waitingTime,
            calories = calories,
            description = description,
            imageUrl = imageUrl,
            categoryId = this.categoryId
        )
    }
    companion object{
        fun fromKey(id:Long) = CategoryType.values().find {
            it.categoryId == id
        } ?: PIZZA
    }

}

class MenuFactory {

    companion object {

        fun getCategories() : List<Category> {

            val categoriesList = mutableListOf<Category>()

            // Con los valores de enum crear la lista de categorias
            CategoryType.values().forEach {
                val category = Category(it.categoryId, it.categoryName, it.imageId, it.categoryOrdinal)
                categoriesList.add(category)
            }

            return categoriesList
        }


        fun getProducts() : List<Product> {

            val productsList = mutableListOf<Product>()

            CategoryType.values().forEach {
                productsList.addAll(getProductsByCategoryType(it))
            }

            return productsList
        }


        private fun getProductsByCategoryType(type: CategoryType): List<Product> {
            return when (type) {
                CategoryType.PIZZA -> getPizzas(type)
                CategoryType.BURGERS -> getBurgers(type)
                CategoryType.HOTDOGS -> getHotDogs(type)
                CategoryType.DRINKS -> getDrinks(type)
                CategoryType.DONUTS -> getDonuts(type)
            }
        }

        private fun getPizzas(type: CategoryType): List<Product> {
            val products = mutableListOf<Product>()


            products.apply {
                add(
                    type.getProduct(
                        "Margherita",
                        8.99,
                        4.5,
                        30,
                        850,
                        "Classic pizza topped with tomato sauce, mozzarella cheese, and fresh basil.",
                        "https://png.pngtree.com/png-clipart/20230912/original/pngtree-top-view-of-pizza-margherita-delicious-picture-image_13032122.png"
                    )
                )
                add(
                    type.getProduct(
                        "Pepperoni",
                        9.99,
                        4.7,
                        35,
                        950,
                        "Pizza topped with tomato sauce, mozzarella cheese, and slices of pepperoni.",
                        "https://png.pngtree.com/png-vector/20230831/ourmid/pngtree-pepperoni-pizza-isolated-with-clipping-path-png-image_9221595.png"

                    )
                )
                add(
                    type.getProduct(
                        "Supreme",
                        11.99,
                        4.6,
                        40,
                        1100,
                        "Loaded pizza with tomato sauce, mozzarella cheese, pepperoni, sausage, onions, peppers, and mushrooms.",
                        "https://png.pngtree.com/png-clipart/20230409/ourmid/pngtree-supreme-pizza-lifted-slice-png-image_6697088.png"
                    )
                )
                add(
                    type.getProduct(
                        "BBQ Chicken",
                        12.99,
                        4.4,
                        45,
                        1150,
                        "Pizza topped with BBQ sauce, grilled chicken, red onions, and cilantro.",
                        "https://static.vecteezy.com/system/resources/previews/024/108/111/original/tasty-bbq-chicken-cheese-pizza-isolated-on-transparent-background-png.png"
                    )
                )
                add(
                    type.getProduct(
                        "Vegetarian",
                        10.99,
                        4.5,
                        35,
                        950,
                        "Pizza loaded with assorted vegetables such as bell peppers, onions, mushrooms, and olives.",
                        "hhttps://www.vhv.rs/dpng/d/481-4812494_vegetarian-pizza-png-transparent-png.png"
                    )
                )
                add(
                    type.getProduct(
                        "Meat Lovers",
                        12.99,
                        4.6,
                        45,
                        1200,
                        "Pizza topped with tomato sauce, mozzarella cheese, pepperoni, sausage, ham, and bacon.",
                        "https://png.pngtree.com/png-clipart/20231111/original/pngtree-the-meat-lovers-pizza-opera-on-a-plate-png-image_13534976.png"
                    )
                )
            }

            return products
        }


        private fun getBurgers(type: CategoryType): List<Product> {
            val burgers = mutableListOf<Product>()


            burgers.apply {
                add(
                    type.getProduct(
                        "Classic Cheeseburger",
                        9.99,
                        4.5,
                        20,
                        850,
                        "A classic cheeseburger with lettuce, tomato, onion, pickles, cheddar cheese, and special sauce.",
                        "https://img.freepik.com/premium-photo/classic-cheeseburger-with-beef-cheese-bacon-tomato-onion-lettuce-isolated-white-background_183587-2707.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Bacon Burger",
                        10.99,
                        4.7,
                        25,
                        950,
                        "A juicy burger topped with crispy bacon, lettuce, tomato, onion, cheddar cheese, and BBQ sauce.",
                        "https://thumbs.dreamstime.com/b/bbq-bacon-burger-hamburger-food-product-mockup-isolated-white-background-generative-ai-bbq-bacon-burger-delicious-269915286.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Mushroom Swiss Burger",
                        11.99,
                        4.3,
                        30,
                        1050,
                        "A savory burger topped with sautéed mushrooms, Swiss cheese, lettuce, tomato, and mayonnaise.",
                        "https://img.freepik.com/premium-photo/classic-mushroom-swiss-burger-perfection-white-background_994921-2742.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Double Patty Burger",
                        12.99,
                        4.6,
                        30,
                        1100,
                        "A hearty burger with two juicy beef patties, American cheese, lettuce, tomato, onion, and special sauce.",
                        "https://www.shutterstock.com/image-photo/tasty-double-patty-cheeseburger-isolated-600nw-2267722981.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Spicy Jalapeño Burger",
                        11.99,
                        4.4,
                        25,
                        1050,
                        "A fiery burger topped with jalapeño peppers, pepper jack cheese, lettuce, tomato, onion, and chipotle mayo.",
                        "https://thumbs.dreamstime.com/b/jalapeno-burger-ai-generative-image-showcasing-its-fiery-flavorful-profile-281185313.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "BBQ Bacon Ranch Burger",
                        12.99,
                        4.6,
                        30,
                        1150,
                        "A mouthwatering burger topped with crispy bacon, cheddar cheese, onion rings, lettuce, tomato, and BBQ ranch sauce.",
                        "https://thumbs.dreamstime.com/b/bbq-bacon-burger-hamburger-food-product-mockup-isolated-white-background-generative-ai-bbq-bacon-burger-delicious-269915286.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Turkey Burger",
                        10.99,
                        4.2,
                        20,
                        800,
                        "A healthier option made with a juicy turkey patty, lettuce, tomato, onion, pickles, and mustard.",
                        "https://static.vecteezy.com/system/resources/previews/027/143/589/non_2x/fresh-tasty-turkey-burger-isolated-on-white-background-png.png"
                    )
                )
            }

            return burgers
        }

        private fun getHotDogs(type: CategoryType): List<Product> {
            val hotDogs = mutableListOf<Product>()


            hotDogs.apply {
                add(
                    type.getProduct(
                        "Classic Hot Dog",
                        5.99,
                        4.3,
                        15,
                        500,
                        "A traditional hot dog served in a bun with ketchup, mustard, relish, and chopped onions.",
                        "https://static8.depositphotos.com/1418795/892/i/450/depositphotos_8926417-stock-photo-hot-dog-with-mustard.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Chili Cheese Dog",
                        6.99,
                        4.5,
                        20,
                        600,
                        "A delicious hot dog topped with homemade chili, melted cheddar cheese, and diced onions.",
                        "https://img.freepik.com/premium-photo/hot-dog-with-chili-cheese-onions-it_899870-9528.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Bacon-Wrapped Hot Dog",
                        7.99,
                        4.7,
                        25,
                        700,
                        "A savory hot dog wrapped in crispy bacon and topped with grilled onions, jalapeños, and BBQ sauce.",
                        "https://static.vecteezy.com/system/resources/previews/027/144/961/non_2x/tasty-bacon-wrapped-hot-dog-isolated-on-transparent-background-png.png"
                    )
                )
                add(
                    type.getProduct(
                        "Chicago Style Dog",
                        6.99,
                        4.4,
                        20,
                        600,
                        "A classic Chicago-style hot dog topped with yellow mustard, chopped onions, sweet pickle relish, a dill pickle spear, tomato slices, pickled sport peppers, and a dash of celery salt.",
                        "https://t3.ftcdn.net/jpg/03/85/94/82/360_F_385948259_AEtOKJ5FOZMufSg14gUoK5JQP9s85vd6.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Jalapeño Cheddar Dog",
                        7.99,
                        4.5,
                        25,
                        700,
                        "A spicy hot dog topped with melted cheddar cheese, sliced jalapeños, and chipotle mayo.",
                        "https://img.freepik.com/premium-photo/hot-dog-with-white-background_899870-9078.jpg"
                    )
                )
            }

            return hotDogs
        }


        private fun getDrinks(type: CategoryType): List<Product> {
            val sodas = mutableListOf<Product>()


            sodas.apply {
                add(
                    type.getProduct(
                        "Cola",
                        1.99,
                        4.5,
                        5,
                        150,
                        "Classic cola soda with a refreshing and bubbly taste.",
                        "https://www.coca-cola.com/content/dam/onexp/gb/en/brands/coca-cola-original-taste/Product-Information-Section-coca-cola-original-taste.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Lemon Lime Soda",
                        1.99,
                        4.3,
                        5,
                        140,
                        "Refreshing lemon-lime soda with a tangy citrus flavor.",
                        "https://beverages2u.com/wp-content/uploads/2019/05/0007800000038_C.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Orange Soda",
                        1.99,
                        4.7,
                        5,
                        160,
                        "Sweet and tangy orange soda bursting with citrus flavor.",
                        "https://cdn11.bigcommerce.com/s-x3kq5bcr0e/images/stencil/608x608/products/7171/8441/Fanta-Orange-Soft-Drink-Can-375mL__27778.1702204762.png"
                    )
                )
                add(
                    type.getProduct(
                        "Root Beer",
                        1.99,
                        4.4,
                        5,
                        170,
                        "Smooth and creamy root beer with hints of vanilla and spices.",
                        "https://thepartysource.com/image/cache/catalog/inventory/A-AND-W-ROOT-BEER-12-PACK-CANS-500x500.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Ginger Ale",
                        1.99,
                        4.6,
                        5,
                        130,
                        "Refreshing ginger ale with a crisp and slightly spicy taste.",
                        "https://cdn3.didevelop.com/public/product_images/53/533_d4102000abd4a0e9ca9048bf2d413666.jpg"
                    )
                )
            }

            return sodas
        }


        private fun getDonuts(type: CategoryType): List<Product> {
            val donuts = mutableListOf<Product>()


            donuts.apply {
                add(
                    type.getProduct(
                        "Glazed Donut",
                        1.49,
                        4.5,
                        5,
                        250,
                        "Classic yeast-raised donut coated with a sweet and shiny glaze.",
                        "https://www.calorieking.com/food-images/us/cad163d0-bf56-4e84-8d11-e8ccf75d4d5c.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Chocolate Frosted Donut",
                        1.79,
                        4.6,
                        5,
                        280,
                        "Soft and fluffy donut with a rich chocolate frosting.",
                        "https://i.pinimg.com/474x/09/01/c0/0901c0de4a544d5e6e3147585965e9d9.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Sprinkle Donut",
                        1.99,
                        4.7,
                        5,
                        300,
                        "Colorful donut covered in rainbow sprinkles for a fun and festive treat.",
                        "https://www.shutterstock.com/image-photo/sweet-strawberry-glazed-donuts-sprinkles-600nw-2276533661.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Maple Bar",
                        2.29,
                        4.6,
                        5,
                        320,
                        "Long and rectangular-shaped donut topped with rich maple glaze.",
                        "https://t3.ftcdn.net/jpg/06/22/73/58/360_F_622735811_UScX8J5lSFzHfP2YEjkHinqolV9mNpxX.jpg"
                    )
                )
                add(
                    type.getProduct(
                        "Jelly Filled Donut",
                        1.99,
                        4.4,
                        5,
                        270,
                        "Soft and pillowy donut filled with sweet fruit jelly.",
                        "https://banner2.cleanpng.com/20180526/ovx/kisspng-donuts-berliner-sufganiyah-beignet-cream-5b096847a13e95.2964701715273431756605.jpg"
                    )
                )
            }

            return donuts
        }



    }
}
