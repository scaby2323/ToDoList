class ShoppingCartItem(initItem: Item, initQuantity: Int) {
    val item = initItem
    val quantity = initQuantity
}

class ShoppingCart(initList : List[ShoppingCartItem] = Nil ) {
    
    var items: List[ShoppingCartItem] = initList
    
    def addToCart(item: Item, qtToAdd: Int = 1): Unit = {
        items = checkList(items, item, qtToAdd)
    }

    def checkList(items: List[ShoppingCartItem], itemToAdd: Item, qtToAdd: Int): List[ShoppingCartItem] = {
        items match {
            case (item :: tl) => 
                if (item.item == itemToAdd) new ShoppingCartItem(item.item, item.quantity + qtToAdd) :: tl 
                else item :: (checkList(tl, itemToAdd, qtToAdd))
            case Nil          => List(new ShoppingCartItem(itemToAdd, qtToAdd))
        }
    }
    
    def getLength() : Int = {
        recList(items)
    }

    def recList(list: List[ShoppingCartItem]) : Int = {
        list match {
            case (item :: tl) => item.quantity + recList(tl)
            case Nil       => 0 
        }
    }

    def removeItem(item: Item, quantity: Int = 1): Unit = {
        items = removeFromList(items, item, quantity)
    }
    
    def getTotal(): Double = {
        items.foldLeft(0.0)((acc, item) => acc + item.item.itemPrice * item.quantity) 
    }

    def getByCategory(category: String): ShoppingCart = {
        new ShoppingCart(items.filter(item => item.item.itemCat == category) )
    }

    def applyDiscount(discount: Double => Double): Unit = {
        items = items.map(item => (new ShoppingCartItem(new Item(item.item.itemName, discount(item.item.itemPrice), (item.item.itemCat)), item.quantity))) 
    }

    def printCart() : String = {
        items.foldLeft("")((acc: String, item: ShoppingCartItem) => acc + "Item: " + item.item.itemName + " Price: " +item.item.itemPrice + " Quantity:" + item.quantity + "\n")
    }

    def removeFromList(lst: List[ShoppingCartItem], itemToRemove: Item, quantityToRemove: Int): List[ShoppingCartItem] = {
        lst match {
            case (hd :: tl) => 
                if (hd.item.itemName == itemToRemove.itemName) {
                    val newQt = hd.quantity - quantityToRemove
                    if (newQt <= 0) tl 
                    else new ShoppingCartItem(hd.item, newQt) :: tl
                } else (hd :: removeFromList(tl, itemToRemove, quantityToRemove))
            case Nil => Nil
        }
    }
}