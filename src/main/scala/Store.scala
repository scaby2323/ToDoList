class Store() {
	val items: List[Item] = List(
		new Item("a", 10.0, "x"), 
		new Item("b", 20.0, "x"), 
		new Item("c", 30.0, "y"),
		new Item("d", 40.0, "z"),
		new Item("e", 50.0, "z"), 
		new Item("f", 60.0, "y"), 
		new Item("g", 70.0, "x"),
		new Item("h", 80.0, "w")
	)

	def getItem(name: String) : Item = {
		loopItems(name, items)
	}

	def loopItems(name: String, lst: List[Item]) : Item = {
		lst match {
			case (i :: tl) => if (i.name == name) i else loopItems(name, tl)
			case Nil       => new Item(name, 10000.0, "new")
		}
	}

	def printStore() : Unit = {
		items.map(i => i.printItem())
	}
}