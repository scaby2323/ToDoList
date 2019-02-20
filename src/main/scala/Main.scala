object Main extends App{
    
    val options = """    
    - add <item> : to add a item to the cart 
    - remove <item> : remove the item 
    - category <category> : only see items of provided category
    - discount <discount> : select a discount to apply to cart (hallOff, twoOff)
    - total : get total price of cars
    - show : print entire cart
    - test : run tests
    - quit : to quit the program
    """

    println("Start making your To Do list. Use the following commands: ")
    
    val scanner = new java.util.Scanner(System.in)
    
    var quit = false
    
    // Uncomment as you're ready to test new functions
    val tester = new Test()

    val parser = new Parser()
    val cart = new ShoppingCart()
    val store = new Store()

    while(!quit) {
        println(options)
        val input = scanner.nextLine()
        val (instr, value) = parser.parse(input)
        instr match {
            case "add"      => {
                cart.addToCart(store.getItem(value)); println("Added " + value + " to cart")
                cart.printCart()
            }
            case "remove"   => {
                cart.removeItem(store.getItem(value)); println("Removed " + value + " from cart")
                cart.printCart()
            }
            case "total"    => println("Your total is $" + cart.getTotal())
            case "discount" => cart.applyDiscount(value.toDouble).printCart()
            case "category" => {
                println("Items in category " + value + ": ");
                cart.getByCategory(value).printCart()
            }
            case "show"     => cart.printCart()
            case "test"     => tester.run()
            case "quit"     => quit = true
            case _          => println("Not a valid response")
        }
    }
}