import kotlin.system.exitProcess

fun main(args: Array<String>) {

    //create function in order to restart program
    fun cubicleProblem(i: Int) {
        println()
        //First we ask how many cubicles can only be singles and how many can be doubles to get a total number of cubicles
        println("How many cubicles cannot be doubled?")
        val uCubicles = Integer.valueOf(readLine())
        println()

        //ask how many cubicles the person wants that are abe to double up
        println("How many cubicles can be doubled?")
        val dCubicles = Integer.valueOf(readLine())
        println()

        //create an unchangeable variable that sums both the single and the double cubicles
        val sumCubicles = uCubicles + dCubicles
        println("You hve a total of $sumCubicles cubicles.")
        println()

        //Ask what is the total number of employees
        println("How many employees total?")

        //turn their "String" input into an integer
        val employees = Integer.valueOf(readLine())
        println()

        //ask how productive doubled employees are (in whole numbers), then transform it into floats
        print("How productive (in %) is each person working in a doubled cubicle?")
        println()
        val avgDoublePercentage: Float = readLine()!!.toFloat()
        val newAvgDoublePercentage = (avgDoublePercentage / 100f)

        println()

        //case where there are 0 or more employees
        if (employees <= 0) {
            println("With zero or negative employees there is no productivity and no utility!")
            println()
            println("Would you like to restart? (Type 'yes')\n")
            val restartEverything1 = readLine()
            println()
            if (restartEverything1 == "yes") {
                cubicleProblem(1)
            } else
                exitProcess(0)
        }
        //if number of employees is less than or equal to the number of cubicles then there is no work to be done, print
        //100% productivity and the % of utility and return
        if (employees == (uCubicles + dCubicles)) {
            println("You have 100% productivity and 100% utility, no need for anyone to work from home!")
            println()
            println("Would you like to restart? (Type 'yes', if not type 'no')\n")
            val restartEverything2 = readLine()
            println()
            if (restartEverything2 == "yes") {
                cubicleProblem(1)
            }
            else
                exitProcess(0)
        }
        //case where the number of employees is less than the number of cubicles
        else if (employees < sumCubicles) {
            println("You have 100% productivity, but utility is only at ${(employees * 100 / sumCubicles)}%, no need for anyone" +
                    " to work from home. \nYou need to have ${sumCubicles - employees} more employees to reach 100% utility.")
            println()
            println("Would you like to restart? (Type 'yes', if not type 'no')\n")
            val restartEverything3 = readLine()
            println()
            if (restartEverything3 == "yes") {
                cubicleProblem(1)
            }
            else
                exitProcess(0)
        }
        //case where the sum of cubicles is zero or less and where user inputs negative for either number of cubicles
        else if (sumCubicles <= 0 || uCubicles < 0 || dCubicles < 0) {
            println("You need to input more than 0 or a negative for the number of cubicles!")
            cubicleProblem(1)
        } else {

            println("Assuming that employees working in single cubicles are 100% productive.")
            println()
            //create variables for the best case scenario, maximizing the number of single cubicles
            val bestCase = sumCubicles
            val bestWFH = employees - bestCase

            //These are the variables for the first worst case scenario that had to be made global in this specific loop
            //current number of people at work at a specific time (not including those working from home)
            val numPeopleAtWork = (2 * dCubicles) + uCubicles
            //total count of people in double cubicles
            val doublePeople = dCubicles * 2
            //number of people that will work from home in these conditions
            val worstPeopleWHF = employees - (numPeopleAtWork)
            //calculates the worst probability possible for situation with these constraints
            val worstPerProbability = ((uCubicles.toFloat() + (newAvgDoublePercentage * doublePeople)) / numPeopleAtWork.toFloat())
            //makes that probability a percentage
            val worstRealProbability = 100 * worstPerProbability

            //These are the variables for the second worst case scenario that had to be made global in this specific loop
            //current number of people at work at a specific time (not including those working from home)
            val worstDoubleCubes = employees - sumCubicles
            //total count of people in double cubicles
            val doublePeopleWorst = worstDoubleCubes * 2
            //gets the number of single cubes
            val worstSingleCubes = sumCubicles - worstDoubleCubes
            //current number of people at work at a specific time (not including those working from home)
            val newWorstNumPeople = doublePeopleWorst + worstSingleCubes
            //calculates the worst probability possible for situation with these constraints
            val worstPreProb = ((worstSingleCubes.toFloat() + (newAvgDoublePercentage * doublePeopleWorst)) / newWorstNumPeople.toFloat())
            //makes that probability a percentage
            val worstProbability = 100 * worstPreProb

            println("-----------------------------------------------------------------------------------------------------" +
                    "---------------------")
            println("Best case scenario (100% average productivity): " +
                    "$bestCase single cubicles, 0 doubled cubicles and ${if (bestWFH == 1) "$bestWFH person" else "$bestWFH people"} working from home.\n" +
                    "-----------------------------------------------------------------------------------------------------" +
                    "---------------------")
            println()

            //This worst case will print of the total number of employees surpasses the total number of seats if every available seat is used
            if ((2 * dCubicles) + uCubicles < employees) {
                println("You have too many employees compared to the number of cubicles. Even in the worst case, you will have people working from home.\n\n" +
                        "If you would like to quit the program and restart type 'r'.\n\n" +
                        "If you would like to continue with the current number of cubicles, type 'c'.\n\n")
                val answerQ = readLine()
                println()
                if (answerQ == "r") {
                    cubicleProblem(1)
                } else if (answerQ == "c") {
                    println("You may continue.\n")
                } else {
                    println("That doesn't match the requested string, now exiting program.")
                    return
                }

                println("-----------------------------------------------------------------------------------------------------" +
                        "--------------------------")
                println("Worst case scenario (${"%.2f".format(worstRealProbability)}% average productivity): " +
                        "$uCubicles single cubicles, $dCubicles doubled cubicles and " +
                        "${if (worstPeopleWHF == 1) "$worstPeopleWHF person" else "$worstPeopleWHF people"} people working from home.\n" +
                        "-----------------------------------------------------------------------------------------------------" +
                        "--------------------------")
                println()

            }
            ///This worst case will print of the total number of employees is less than the total number of seats if every available seat is used
            else {

                println("-----------------------------------------------------------------------------------------------------" +
                        "---------------------")
                println("Worst case scenario (${"%.2f".format(worstProbability)}% average productivity): " +
                        "$worstSingleCubes single cubicles, $worstDoubleCubes doubled cubicles and 0 people working from home.\n" +
                        "-----------------------------------------------------------------------------------------------------" +
                        "---------------------")
                println()
            }
            //create a boolean to manage loop that will restart questions if an incorrect answer is inputted
            var boolean = true
            while (boolean == true) {
                println("What average productivity (in %) would you like?")
                val desiredProductivityPre = (readLine())
                val desiredProductivity = desiredProductivityPre!!.toFloat()
                println()
                if (desiredProductivity > 100) {
                    println("The desired productivity exceeds the best case average productivity and is not attainable.")
                    println()
                    println("Can you please re-enter the desired probability.")
                    println()
                } else if (desiredProductivity < worstProbability.toInt() || desiredProductivity < worstRealProbability.toInt()) {
                    println("The desired productivity is smaller than the worst case average productivity and is not attainable.")
                    println()
                    println("Can you please re-enter the desired probability.")
                    println()
                } else if (desiredProductivity == 100f) {
                    println("The desired productivity is the same as the best case average productivity.")
                    println()
                    boolean = false
                } else if (desiredProductivity == worstProbability || desiredProductivity == worstRealProbability) {
                    println("The desired productivity is the same as the worst case average productivity.")
                    println()
                    boolean = false
                } else {

                    //#1
                    //This set starts at the best case scenario and keeps decreasing until the productivity is the same
                    //or less than the desired productivity
                    var newSinCubes = sumCubicles
                    //println("Single cubicles at the beginning = $newSinCubes")
                    val newDoubCubes = 0
                    var newDoublePoples = newDoubCubes * 2f
                    //println("People in doubled at the beginning = $newDoublePoples")
                    var newSumOfWorkEmp = newSinCubes + newDoublePoples
                    //println("The sum of the employees = $newSumOfWorkEmp")
                    var productivity = ((newSinCubes.toFloat() + (newAvgDoublePercentage * newDoublePoples)) / newSumOfWorkEmp) * 100

                    while (productivity > desiredProductivity) { //&& (newSumOfWorkEmp + (employees - newSumOfWorkEmp)) <= employees && newSinCubes < uCubicles) {
                        newSinCubes -= 1
                        newDoublePoples += 2
                        newSumOfWorkEmp = newSinCubes + newDoublePoples
                        productivity = ((newSinCubes.toFloat() + (newAvgDoublePercentage * newDoublePoples)) / newSumOfWorkEmp) * 100
                    }
                    //#2
                    var newSinCubesb = uCubicles
                    //println("Single cubicles at the beginning = $newSinCubes")
                    val newDoubCubesb = dCubicles
                    val newWFHb = worstPeopleWHF
                    var newWFHb1 = newWFHb
                    var newDoublePoplesb = newDoubCubesb * 2
                    //println("People in doubled at the beginning = $newDoublePoples")
                    var newSumOfWorkEmpb = newSinCubesb + newDoublePoplesb
                    //println("The sum of the employees = $newSumOfWorkEmp")
                    var productivityb = ((newSinCubesb.toFloat() + (newAvgDoublePercentage * newDoublePoplesb)) / newSumOfWorkEmpb) * 100

                    while (productivityb < desiredProductivity && newWFHb <= newWFHb1) { //) {
                        newSinCubesb += 1
                        newDoublePoplesb -= 2
                        newWFHb1 += 1
                        newSumOfWorkEmpb = newSinCubesb + newDoublePoplesb
                        productivityb = ((newSinCubesb.toFloat() + (newAvgDoublePercentage * newDoublePoplesb)) / newSumOfWorkEmpb) * 100

                    }

                    //#3
                    var newSinCubesc = worstSingleCubes
                    //println("Single cubicles at the beginning = $newSinCubes")
                    val newDoubCubesc = worstDoubleCubes
                    var newWFHc = 0
                    //var newWFHc1 = newWFHc
                    var newDoublePoplesc = newDoubCubesc * 2
                    //println("People in doubled at the beginning = $newDoublePoples")
                    var newSumOfWorkEmpc = newSinCubesc + newDoublePoplesc
                    //println("The sum of the employees = $newSumOfWorkEmp")
                    var productivityc = ((newSinCubesc.toFloat() + (newAvgDoublePercentage * newDoublePoplesc)) / newSumOfWorkEmpc) * 100

                    while (productivityc < desiredProductivity) {
                        newSinCubesc += 1
                        newDoublePoplesc -= 2
                        newWFHc += 1
                        newSumOfWorkEmpc = newSinCubesc + newDoublePoplesc
                        productivityc = ((newSinCubesc.toFloat() + (newAvgDoublePercentage * newDoublePoplesc)) / newSumOfWorkEmpc) * 100

                    }
                    //if the desired probability is equal to the first, second or third calculated probability then it will just print
                    //that as an average probability
                    if (desiredProductivity.toInt() != productivity.toInt() && desiredProductivity.toInt() != productivityb.toInt()
                            && desiredProductivity.toInt() != productivityc.toInt()) {


                        println("Based on your desired productivity of $desiredProductivity%:")
                        println()
                        println("-----------------------------------------------------------------------------------------------------" +
                                "---------------------------")
                        println("You can achieve ${"%.2f".format(productivity)}% productivity on average if you have $newSinCubes single " +
                                "cubicles, ${newDoublePoples.toInt() / 2} double cubicles and " +
                                "${if ((employees - newSumOfWorkEmp.toInt()) == 1) "${employees - newSumOfWorkEmp.toInt()} " +
                                        "person" else "${employees - newSumOfWorkEmp.toInt()} people"} working from home.")
                        println("-----------------------------------------------------------------------------------------------------" +
                                "---------------------------")
                        println()


                        //This set starts at the worst case scenario and keeps increasing until the productivity is the same
                        //or greater than the desired productivity, but it only happens if the sum of the seats is less than the sum
                        //of employees
                        if ((2 * dCubicles) + uCubicles < employees) {
                            if (productivity.toInt() != productivityb.toInt()) {
                                println("OR")
                                println()
                                println("-----------------------------------------------------------------------------------------------------" +
                                        "---------------------------")
                                println("You can achieve ${"%.2f".format(productivityb)}% productivity on average if you have $newSinCubesb single " +
                                        "cubicles, ${newDoublePoplesb / 2} double cubicles and ${if (newWFHb1 == 1) "$newWFHb1 person" else
                                            "$newWFHb1 people"} working from home.")
                                println("-----------------------------------------------------------------------------------------------------" +
                                        "---------------------------")
                                println()
                            }
                        }
                        //This set starts at the worst case scenario and keeps increasing until the productivity is the same
                        //or greater than the desired productivity, but it only happens if the sum of the seats is greater than the sum
                        //of employees
                        else {
                            if (productivity.toInt() != productivityc.toInt()) {
                                println("OR")
                                println()
                                println("-----------------------------------------------------------------------------------------------------" +
                                        "---------------------------")
                                println("You can achieve ${"%.2f".format(productivityc)}% productivity on average if you have $newSinCubesc single " +
                                        "cubicles, ${newDoublePoplesc / 2} double cubicles and ${if (newWFHc == 1) "$newWFHc person" else "$newWFHc " +
                                                "people"} working from home.")
                                println("-----------------------------------------------------------------------------------------------------" +
                                        "---------------------------")
                                println()
                            }
                        }
                    }
                    else if (desiredProductivity.toInt() == productivity.toInt()){
                        println("Based on your desired productivity of $desiredProductivity%:")
                        println()
                        println("-----------------------------------------------------------------------------------------------------" +
                                "---------------------------")
                        println("You can achieve ${"%.2f".format(productivity)}% productivity on average if you have $newSinCubes single " +
                                "cubicles, ${newDoublePoples.toInt() / 2} double cubicles and " +
                                "${if ((employees - newSumOfWorkEmp.toInt()) == 1) "${employees - newSumOfWorkEmp.toInt()} " +
                                        "person" else "${employees - newSumOfWorkEmp.toInt()} people"} working from home.")
                        println("-----------------------------------------------------------------------------------------------------" +
                                "---------------------------")
                        println()
                    }
                    else if (desiredProductivity.toInt() == productivityb.toInt()){
                        println("Based on your desired productivity of $desiredProductivity%:")
                        println()
                        println("-----------------------------------------------------------------------------------------------------" +
                                "---------------------------")
                        println("You can achieve ${"%.2f".format(productivityb)}% productivity on average if you have $newSinCubesb single " +
                                "cubicles, ${newDoublePoplesb / 2} double cubicles and ${if (newWFHb1 == 1) "$newWFHb1 person" else
                                    "$newWFHb1 people"} working from home.")
                        println("-----------------------------------------------------------------------------------------------------" +
                                "---------------------------")
                        println()
                    }
                    else if (desiredProductivity.toInt() == productivityc.toInt()){
                        println("Based on your desired productivity of $desiredProductivity%:")
                        println()
                        println("-----------------------------------------------------------------------------------------------------" +
                                "---------------------------")
                        println("You can achieve ${"%.2f".format(productivityc)}% productivity on average if you have $newSinCubesc single " +
                                "cubicles, ${newDoublePoplesc / 2} double cubicles and ${if (newWFHc == 1) "$newWFHc person" else "$newWFHc " +
                                        "people"} working from home.")
                        println("-----------------------------------------------------------------------------------------------------" +
                                "---------------------------")
                        println()
                    }
                    println("If you would like to select a different desired productivity type 'yes'\n" +
                            "If not, type 'no'.")
                    println()
                    var newChoice = readLine()
                    boolean = newChoice == "yes"
                    println()
                }
            }
        }

        println("Would you like to restart? (Type 'yes' to restart, 'no' to exit)\n")

        val restartEverything4 = readLine()
        println()
        if (restartEverything4 == "yes") {
            cubicleProblem(1)
        } else
            exitProcess(0)
        println("Goodbye!")
        if (i == 1) {
            exitProcess(0)
        }
    }
    cubicleProblem(0)
}