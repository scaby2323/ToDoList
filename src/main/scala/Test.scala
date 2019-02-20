class Test {
	def compareCart(l1: ShoppingCart, l2: ShoppingCart): Boolean = {
		(l1.items, l2.items) match {
			case ((h1 :: t1), (h2 :: t2)) => compareItem(h1, h2)
			case (_, _) => false 
		}
	}

	def compareItem(i1: Item, i2: Item) : Boolean = {
		i1.name == i2.name && i1.price == i2.price && i1.category == i2.category
	}

	def testShoppingCart(l1: ShoppingCart, l2: ShoppingCart) : Unit = {
		if(compareCart(l1, l2)) {
			println("PASSED")
		} else {
			println("FAILED")
			println("Actual: " + l1.printCart())
			println("Expected: " + l2.printCart())
		}
		println("--------")
	}	

	def testValues(v1: Int , v2: Int) : Unit = {
		if (v1 == v2) println("PASSED")
		else {
	 		println("FAILED")
			println("Actual: " + v1) 
			println("Expected: " + v2)
		}
	}

	def testDoubles(v1: Double, v2: Double) : Unit = {
		if (v1 == v2) println("PASSED")
		else {
	 		println("FAILED")
			println("Actual: " + v1) 
			println("Expected: " + v2)
			
		}
	}

	val item1 = new Item("a", 10.0, "x") 
	val item2 = new Item("b", 20.0, "x") 
	val item3 = new Item("c", 30.0, "y")
	val item4 = new Item("d", 40.0, "z")

	var cart = new ShoppingCart()

	def run() : Unit = {
		println("--------Add items to cart tests--------")
		cart.addToCart(item1)
		// cart = item1
		val expectedCart1 = new ShoppingCart(List(item1))
		testShoppingCart(cart, expectedCart1)

		cart.addToCart(item2)
		// cart = item1, item2
		val expectedCart2 = new ShoppingCart(List(item1, item2))
		testShoppingCart(cart, expectedCart2)

		println("--------Remove task from list--------")
		cart.removeItem(item1)
		// cart = item2
		val expectedCart3 = new ShoppingCart(List(item2))
		testShoppingCart(cart, expectedCart3)

		cart.addToCart(item3)
		// cart = item2, item3
		val expectedCart4 = new ShoppingCart(List(item2, item3))
		testShoppingCart(cart, expectedCart4)

		cart.removeItem(item3)
		// cart = item2
		val expectedCart5 = new ShoppingCart(List(item2))
		testShoppingCart(cart, expectedCart5)

		println("--------Get size of cart--------")
		testValues(cart.getLength(), 1) 

		cart.addToCart(item4)
		// cart = item2, item4 
		testValues(cart.getLength(), 2)

		println("--------Get total--------")
		testDoubles(cart.getTotal(), 60.0) 

		cart.removeItem(item2)
		// cart = item4
		testDoubles(cart.getTotal(), 40.0)

		println("--------Filter by Category--------")
		cart = new ShoppingCart()
		cart.addToCart(item1)
		cart.addToCart(item2)
		cart.addToCart(item3) 
		// cart = item1, item2, item3
		val xShoppingCart = cart.getByCategory("x") 
		val expectedX = new ShoppingCart(List(item1, item2))
		testShoppingCart(xShoppingCart, expectedX)

		val yShoppingCart = cart.getByCategory("y")
		val expectedY = new ShoppingCart(List(item3))
		testShoppingCart(yShoppingCart, expectedY)


		println("--------Apply discount--------")

		def halfOff = 0.5
		def twoOff = 0.25

		cart = new ShoppingCart()
		cart.addToCart(item1)
		cart.addToCart(item2)
		cart.applyDiscount(halfOff)

		val item5 = new Item("a", 5.0, "x") 
		val item6 = new Item("b", 10.0, "x")
		val expectedCart6 = new ShoppingCart(List(item5, item6))
		testShoppingCart(cart, expectedCart6)

		cart = new ShoppingCart()
		cart.addToCart(item3)
		cart.addToCart(item4)
		cart.applyDiscount(twoOff)

		val item7 = new Item("c", 22.5, "y")
		val item8 = new Item("d", 30,  "z")
		val expectedCart7 = new ShoppingCart(List(item7, item8))
		testShoppingCart(cart, expectedCart7)
	}
}