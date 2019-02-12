class TaskList(taskList: List[Task] = Nil) {
    
    var tasks: List[Task] = taskList
    
    def addTask(task: String): Unit = {
        val newTask = new Task(task, 1)
        tasks = tasks :+ newTask
    }
    
    def removeTask(task: Integer): Unit = {
        tasks = removeFromList(0, tasks, task)
    }
    
    def updateTask(task: Integer) : Unit = {
        tasks = updateInList(0, tasks, task)
    } 
    
    def getCompleted() : TaskList = {
        return new TaskList(tasks.filter(t => t.status == 3))
    }

    def prettyPrint() : Unit = {
        tasks.foldLeft("")((acc: String, task: Task) => acc + task.prettyPrint() + "\n")
    }

    def removeFromList(currIdx: Integer, lst: List[Task], idxToRemove: Integer): List[Task] = {
        lst match {
            case (hd :: tl) => if (currIdx == idxToRemove) tl else (hd :: removeFromList(currIdx+1, tl, idxToRemove))
            case Nil => Nil
        }
    }
    
    def updateInList(currIdx: Integer, lst: List[Task], idxToUpdate: Integer): List[Task] = {
        lst match {
            case (hd :: tl) => if (currIdx == idxToUpdate) hd.updateStatus() :: tl else (hd :: updateInList(currIdx + 1, tl, idxToUpdate))
            case Nil => Nil 
            
        }
    }
}