class Task(details: String, status: Int, index: Int) {
    
    val taskDetails = details
    val taskStatus = status
    val taskIndex = index
    

    def getStatus() : String = {
        this.taskStatus match {
            case 1 => "To Do"
            case 2 => "In Progress"
            case 3 => "Completed"
        }
    }
    
    def updateStatus() : Task = {
        this.taskStatus match {
            case 1 => new Task(taskDetails, 2, taskIndex)
            case 2 => new Task(taskDetails, 3, taskIndex)
            case 3 => throw new IllegalArgumentException("Task is already completed")
        }
    }
    
    def prettyPrint() : Unit = {
        println("Task " + taskIndex + ": " + taskDetails + " Status: " + getStatus())
    }
}