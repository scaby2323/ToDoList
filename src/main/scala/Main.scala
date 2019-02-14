object Main extends App{
    
    val options = """    
    - add <task> : to add a task to the list 
    - remove <index> : remove the task at the provided index
    - update <index> : update task at the given index (To Do, In Progress, Completed)
    - completed : print all of the completed tasks
    - show : print all of the tasks
    - test : test functionality
    - quit : to quit the program
    """

    println("Start making your To Do list. Use the following commands: ")
    
    val scanner = new java.util.Scanner(System.in)
    
    var quit = false
    
    val parser = new Parser()
    val tester = new Test()

    while(!quit) {
        println(options)
        val input = scanner.nextLine()
        val (instr, value) = parser.parse(input)
        instr match {
            // case "add"       => taskList.addTask(value)
            // case "remove"    => taskList.removeTask(value.toInt)
            // case "update"    => taskList.updateTask(value.toInt)
            // case "completed" => taskList.getCompleted().prettyPrint()
            // case "show"      => taskList.prettyPrint()
            case "test"      => tester.run()
            case "quit"      => quit = true
            case _           => println("Not a valid response")
        }
    }
}