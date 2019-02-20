class ShoppingCart(initList : List[Item] = Nil ) {
    
    var items: List[Item] = initList
    
    def addToCart(newItem: Item): Unit = {
        items = items :+ newItem
    }
    
    def getLength() : Int = {
        recList(items)
    }

    def recList(list: List[Item]) : Int = {
        list match {
            case (x :: tl) => 1 + recList(tl)
            case Nil => 0 
        }
    }

    def removeItem(item: Item, items: List[Item] = items): List[Item] = {
        items match {
            case (hd :: tl) => if (hd.name == item.name) tl else (hd :: removeItem(item, tl))
            case Nil => Nil
        }
    }

    def getByCategory(category: String) : ShoppingCart = {
        new ShoppingCart(items.filter(item => item.category == category))
    }

    def checkOut() : Unit = {
        items = Nil
    }

    def applyDiscount(discount: Double): ShoppingCart = {
        new ShoppingCart(items.map(item => item.updatePrice(item.price * (1-discount))))
    }

    def getTotal() : Double = {
        items.foldLeft(0.0)((acc, item) => acc + item.price)
    }

    def printCart() : Unit = {
        items.map(item => item.printItem())
    }
}