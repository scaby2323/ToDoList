object Main extends App{

    val taskList = new TaskList()
    val doLaundry = new Task("do laundry", 1)
    val buyGroceries = new Task("buy groceries", 1)
    val buyGroceries_2 = new Task("buy groceries", 2)
    val buyGroceries_3 = new Task("buy groceries", 3)
    val cleanRoom = new Task("clean room", 1)

    // println("--------Test Set Status--------")
    // doLaundry.setStatus(2)
    // val doLaundryTest = new Task("do laundry", 2)
    // println(compareTask(doLaundry, doLaundryTest))
    
    // buyGroceries.setStatus(3)
    // val buyGroceriesTest = new Task("buy groceries", 3)
    // println(compareTask(buyGroceries, buyGroceriesTest))

    println("--------Add task to list tests--------")
    taskList.clearList()
    
    taskList.addTask("do laundry") 
    val x = List(doLaundry)
    test(taskList.tasks, List(doLaundry))
    
    taskList.addTask("buy groceries")
    test(taskList.tasks, List(doLaundry, buyGroceries))
    
    taskList.addTask("clean room")
    test(taskList.tasks, List(doLaundry, buyGroceries, cleanRoom))
    
    println("--------Remove task from list--------")
    taskList.removeTask(0)
    test(taskList.tasks, List(buyGroceries, cleanRoom))
    
    taskList.removeTask(1) 
    test(taskList.tasks, List(buyGroceries))
    
    // println("--------Get size of list--------")
    // taskList.clearList()
    // println(taskList.getLength())
    // println(taskList.getLength() == 0) 

    // taskList.addTask("bake cookies") 
    // taskList.addTask("do laundry")
    // println(taskList.getLength())
    // println(taskList.getLength() == 2)

    println("--------Update task--------")
    taskList.updateTask(0) 
    test(taskList.tasks, List(buyGroceries_2))
    
    taskList.addTask("clean room")
    test(taskList.tasks, List(buyGroceries_2, cleanRoom))
    
    taskList.updateTask(0)
    test(taskList.tasks, List(buyGroceries_3, cleanRoom))
    
    println("--------Get completed--------")
    val completed = taskList.getCompleted().tasks
    test(completed, List(buyGroceries_3))

    val options = """    
    - add <task> : to add a task to the list 
    - remove <index> : remove the task at the provided index
    - update <index> : update task at the given index (To Do, In Progress, Completed)
    - completed : print all of the completed tasks
    - show : print all of the tasks
    - quit : to quit the program
    """

    println("Start making your To Do list. Use the following commands: ")
    
    val scanner = new java.util.Scanner(System.in)
    
    var quit = false
    
    val parser = new Parser()

    while(!quit) {
        println(options)
        val input = scanner.nextLine()
        val (instr, value) = parser.parse(input)
        instr match {
            case "add"       => taskList.addTask(value)
            case "remove"    => taskList.removeTask(value.toInt)
            case "update"    => taskList.updateTask(value.toInt)
            case "completed" => taskList.getCompleted().prettyPrint()
            case "show"      => taskList.prettyPrint()
            case "quit"      => quit = true
            case _           => println("Error try again")
        }
    }

    def compareList(l1: List[Task], l2: List[Task]): Boolean = {
        (l1, l2) match {
            case ((h1 :: t1), (h2 :: t2)) => compareTask(h1, h2)
            case (_, _) => false 
            
        }
    }

    def compareTask(t1: Task, t2: Task) : Boolean = {
        t1.taskDetails == t2.taskDetails && t1.taskStatus == t2.taskStatus
    }
    
    def test(l1: List[Task], l2: List[Task]) : Unit = {
        if(compareList(l1, l2)) {
            println(true)
        } else {
            println("TEST FAILED")
            println("Expected: " + (new TaskList(l2)).prettyPrint())
            println("Actual: " + (new TaskList(l1)).prettyPrint())
        }
    }
}