class Item(itemName: String, itemPrice: Double, itemCat: String) {
	val name = itemName
	var price = itemPrice
	val category = itemCat

	def updatePrice(newPrice: Double): Item = {
		price = newPrice
		this
	}

	def printItem(): Unit = {
		println("Item: " + name + " Price: " + price + " Category: " + category) 
	}
}