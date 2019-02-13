class TaskList(initList : List[Task] = Nil ) {
    
    var tasks: List[Task] = initList
    
    def addTask(task: String): Unit = {
        val newTask = new Task(task, 1)
        tasks = tasks :+ newTask
    }

    // def getLength() : Int = {
    //     tasks.foldLeft(0)((acc: Int, task: Task) => acc + 1)
    // }
    
    def getLength() : Int = {
        recList(tasks)
    }

    def recList(list: List[Task]) : Int = {
        list match {
            case (x :: tl) => 1 + recList(tl)
            case Nil => 0 
        }
    }

    def removeTask(task: Int): Unit = {
        tasks = removeFromList(0, tasks, task)
    }
    
    def updateTask(task: Int) : Unit = {
        tasks = updateInList(0, tasks, task)
    } 
    
    def getCompleted() : TaskList = {
        new TaskList(tasks.filter(t => t.taskStatus == 3))
    }

    def clearList() : Unit = {
        tasks = Nil
    }

    def prettyPrint() : Unit = {
        tasks.foldLeft("", 0)((acc: (String, Int), task: Task) => (acc._1 + task.prettyPrint(acc._2) + "\n", acc._2 + 1))

        // tasks.foldLeft("")((acc: String, task: Task) => acc + task.prettyPrint() + "\n")
    }

    def removeFromList(currIdx: Int, lst: List[Task], idxToRemove: Int): List[Task] = {
        lst match {
            case (hd :: tl) => if (currIdx == idxToRemove) tl else (hd :: removeFromList(currIdx+1, tl, idxToRemove))
            case Nil => Nil
        }
    }
    
    def updateInList(currIdx: Int, lst: List[Task], idxToUpdate: Int): List[Task] = {
        lst match {
            case (hd :: tl) => if (currIdx == idxToUpdate) hd.updateStatus() :: tl else (hd :: updateInList(currIdx + 1, tl, idxToUpdate))
            case Nil => Nil 
            
        }
    }
}