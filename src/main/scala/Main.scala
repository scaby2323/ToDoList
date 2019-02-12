object Main extends App{
    // Your code here!

    val taskList = new TaskList(Nil)
    val doLaundry = new Task("do laundry", 1)
    val buyGroceries = new Task("buy groceries", 1)
    val buyGroceries_2 = new Task("buy groceries", 2)
    val buyGroceries_3 = new Task("buy groceries", 3)
    val cleanRoom = new Task("clean room", 1)
    
    // println("--------Add task to list tests--------")
    // taskList.addTask("do laundry") 
    // val x = List(doLaundry)
    // test(taskList.tasks, List(doLaundry))
    
    // taskList.addTask("buy groceries")
    // test(taskList.tasks, List(doLaundry, buyGroceries))
    
    // taskList.addTask("clean room")
    // test(taskList.tasks, List(doLaundry, buyGroceries, cleanRoom))
    
    // println("--------Remove task from list--------")
    // taskList.removeTask(0)
    // test(taskList.tasks, List(buyGroceries, cleanRoom))
    
    // taskList.removeTask(1) 
    // test(taskList.tasks, List(buyGroceries))
    
    // println("--------Update task--------")
    // taskList.updateTask(0) 
    // test(taskList.tasks, List(buyGroceries_2))
    
    // taskList.addTask("clean room")
    // test(taskList.tasks, List(buyGroceries_2, cleanRoom))
    
    // taskList.updateTask(0)
    // test(taskList.tasks, List(buyGroceries_3, cleanRoom))
    
    // println("--------Get completed--------")
    // val completed = taskList.getCompleted()
    // test(completed, List(buyGroceries_3))


    def compareList(l1: List[Task], l2: List[Task]): Boolean = {
        (l1, l2) match {
            case ((h1 :: t1), (h2 :: t2)) => h1.details == h2.details  && h1.status == h2.status 
            case (_, _) => false 
            
        }
    }
    
    def test(l1: List[Task], l2: List[Task]) : Unit = {
        if(compareList(l1, l2)) {
            println(true)
        } else {
            println("Expected: " + l2.foreach(t => t.prettyPrint()))
            println("Actual: " + l1.foreach(t => t.prettyPrint()))
        }
    }
    println("Start making your To Do list - use add <task>, complete <task index> or quit")
    
    val scanner = new java.util.Scanner(System.in)
    
    var quit = false
    
    while(!quit) {
        val input = scanner.nextLine()
        println(">")
        input match {
            case in if in.startsWith("add ") => taskList.addTask(input.substring(4))
            case in if in.startsWith("remove ") => taskList.removeTask(input.substring(7).toInt)
            case in if in.startsWith("update") => taskList.updateTask(input.substring(7).toInt)
            case in if in.startsWith("completed") => taskList.getCompleted().prettyPrint()
            case in if in.startsWith("show") => taskList.prettyPrint()
            case in if in.startsWith("quit") => quit = true; println("GOODBYE")
            case _ => println("thats not allowed")
        }
    }
}