class Test {
	def compareCart(l1: ShoppingCart, l2: ShoppingCart): Boolean = {
		(l1.items, l2.items) match {
			case ((h1 :: t1), (h2 :: t2)) => compareItemInCart(h1, h2)
			case (_, _) => false 
		}
	}

	def compareItemInCart(i1: ShoppingCartItem, i2: ShoppingCartItem) : Boolean = {
		compareItem(i1.item, i2.item) && i1.quantity == i2.quantity
	}

	def compareItem(i1: Item, i2: Item) : Boolean = {
		i1.itemName == i2.itemName && i1.itemPrice == i2.itemPrice && i1.itemCat == i2.itemCat
	}

	def testShoppingCart(l1: ShoppingCart, l2: ShoppingCart) : Unit = {
		if(compareCart(l1, l2)) {
			println("PASSED")
		} else {
			println("Actual: " + l1.printCart())
			println("Expected: " + l2.printCart())
			println("FAILED")
		}
		println("--------")
	}	

	def testValues(v1: Int , v2: Int) : Unit = {
		if (v1 == v2) println("PASSED")
		else {
	 		println("Actual: " + v1) 
			println("Expected: " + v2)
			println("FAILED")
		}
	}

	def testDoubles(v1: Double, v2: Double) : Unit = {
		if (v1 == v2) println("PASSED")
		else {
	 		println("Actual: " + v1) 
			println("Expected: " + v2)
			println("FAILED")
		}
	}

	val item1 = new Item("a", 10.0, "x") 
	val item2 = new Item("b", 20.0, "x") 
	val item3 = new Item("c", 30.0, "y")
	val item4 = new Item("d", 40.0, "z")

	var cart = new ShoppingCart()

	def run() : Unit = {
		println("--------Add items to cart tests--------")
		cart.addToCart(item1, 1) 
		val expectedCart1 = new ShoppingCart(List(new ShoppingCartItem(item1, 1)))
		testShoppingCart(cart, expectedCart1)

		cart.addToCart(item2, 2)
		val expectedCart2 = new ShoppingCart(List(new ShoppingCartItem(item1, 1), new ShoppingCartItem(item2, 2)))
		testShoppingCart(cart, expectedCart2)

		println("--------Remove task from list--------")
		cart.removeItem(item1)
		val expectedCart3 = new ShoppingCart(List(new ShoppingCartItem(item2, 2)))
		testShoppingCart(cart, expectedCart3)

		cart.addToCart(item3, 4)
		val expectedCart4 = new ShoppingCart(List(new ShoppingCartItem(item2, 2), new ShoppingCartItem(item3, 4)))
		testShoppingCart(cart, expectedCart4)

		cart.removeItem(item3, 2)
		val expectedCart5 = new ShoppingCart(List(new ShoppingCartItem(item2, 2), new ShoppingCartItem(item3, 2)))
		testShoppingCart(cart, expectedCart5)

		println("--------Get size of cart--------")
		testValues(cart.getLength(), 4) 

		cart.addToCart(item4)
		testValues(cart.getLength(), 5)

		println("--------Get total--------")
		testDoubles(cart.getTotal(), 140.0) 

		cart.removeItem(item2, 2)
		cart.removeItem(item3, 2) 
		testDoubles(cart.getTotal(), 40.0)

		println("--------Filter by Category--------")
		cart = new ShoppingCart()
		cart.addToCart(item1)
		cart.addToCart(item2)
		cart.addToCart(item3) 

		val xShoppingCart = cart.getByCategory("x") 
		val expectedX = new ShoppingCart(List(new ShoppingCartItem(item1, 1), new ShoppingCartItem(item2, 1)))
		testShoppingCart(xShoppingCart, expectedX)

		val yShoppingCart = cart.getByCategory("y")
		val expectedY = new ShoppingCart(List(new ShoppingCartItem(item3, 1)))
		testShoppingCart(yShoppingCart, expectedY)

		println("--------Apply discount--------")

		def halfOff(x: Double) = x / 2.0
		def twoOff(x: Double) = x - 2

		cart = new ShoppingCart()
		cart.addToCart(item1)
		cart.addToCart(item2)
		cart.applyDiscount(halfOff)

		val item5 = new Item("a", 5.0, "x") 
		val item6 = new Item("b", 10.0, "x")
		val expectedCart6 = new ShoppingCart(List(new ShoppingCartItem(item5, 1), new ShoppingCartItem(item6, 1)))
		testShoppingCart(cart, expectedCart6)

		cart = new ShoppingCart()
		cart.addToCart(item3)
		cart.addToCart(item4)
		cart.applyDiscount(twoOff)

		val item7 = new Item("c", 28.0, "y")
		val item8 = new Item("d", 38.0,  "z")
		val expectedCart7 = new ShoppingCart(List(new ShoppingCartItem(item7, 1), new ShoppingCartItem(item8, 1)))
		testShoppingCart(cart, expectedCart7)
	}
}