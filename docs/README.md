User Guide 
1.Features
_Todo, Deadline, Event, Done, Find**_ 

1.1 Todo    
    todo is a task without any time or deadlines assigned to: 
    Example: user can add a todo task -- Visit NTU 
    Format: Todo [activity]
    Usage: Todo NTU 
    Expected Output: ---------
                     Got it i va added the this task
                     [T][NotDone]visit NTU Tag:
                     Now you have 1 task in the list
                     ----------
       
1.2 Deadline
    Deadline is deadline task with a date is assigned to
    Format: deadline [activity] [/by yyyy-mm-dd]
    Usage: deadline return book /by 2019-10-19
    Expected Output:----------
                    [D][NotDone] return book (Sun, Oct 19 2019)
                    Now you have 2 task in the list
                    -------
                 
1.3 Event
    Event is a event task with a date is assigned to it. 
    Format: event [activity] [/at yyyy-mm--dd]
    Usage: event project meeting /at 2019-12-24
    Expected Output: ---------
                     [D][NotDone] project meeting (Tue, Dec 24 2019)
                     Now you have 3 task in the list
                     -------


1.4 List 
    Display all the task in the list 
    Format:list
    Usage: list
    Expected Output: --------------------
                    1. [T][NotDone] visit NTU tag:
                    2. [D][NotDone] return book (Sun, Oct 19 2019)
                    3. [E][NotDone] project meeting (Tue, Dec 24 2019)
                    -----------------------
                         
1.5 Done 
    Done command is used to update a task status in the task list 
    Format: done [index of task]
    Usage done 1 
    Expected Output: --------------------
                     1. [T][Done] visit NTU tag:
                     2. [D][NotDone] return book (Sun, Oct 19 2019)
                     3. [E][NotDone] project meeting (Tue, Dec 24 2019)
                     -----------------------
                     
1.6 Find 
    Find command allows user to search his/her task with keywords entered. The apps will return matching words
    Format: find [keywords]
    Usage: find visit
    Expected Output: -----------------
                    Here are the matching tasks in your list
                    1.[T][NoDone] visit NTU tag:
                    -------------------
                    
1.7 Tag
    Add a tag to the task 
    Format: tag [keywords] as [#tag]
    Usage tag visit as work 
    Expected Output: ------------------
                    the tag work is successfully added to visit NTU 
                    -------------------
 
1.8 Delete 
    delete command allows user to deleta a task or event or deadline that he entered
    Format: delete [index of task]
    Usage delete 2
    Expected Output: ------------------
                    Got it, i've removed thi task 
                    [E][NotDone] return book (Sat, Oct 19 2019)
                    Now you have 2 tasks in the list
                    --------------------
 
