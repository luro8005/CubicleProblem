import kotlin.system.exitProcess

fun main(args: Array<String>) {

    fun cubicleProblem(i: Int) {
        println()
        //First we ask how many cubicles can only be singles and how many can be doubles to get a total number of cubicles
        println("How many individual cubicles are there?")
        val uCubicles = Integer.valueOf(readLine())
        //var uCubicles : Int = 4

        println("How many cubicles can be doubled?")
        val dCubicles = Integer.valueOf(readLine())

        val sumCubicles = uCubicles + dCubicles

        //Ask what is the total number of employees
        println("How many employees total?")

        val employees = Integer.valueOf(readLine())

        print("How productive (in %) is each person working in a doubled cubicle?")
        println()
        val avgDoublePercentage: Float = readLine()!!.toFloat()
        val newAvgDoublePercentage = (avgDoublePercentage / 100f)

        println()

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
        if (employees == (uCubicles + (2 * dCubicles))) {
            println("You have 100% productivity and 100% utility, no need for anyone to work from home!")
            println()
            println("Would you like to restart? (Type 'yes')\n")
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
            println("Would you like to restart? (Type 'yes')\n")
            val restartEverything3 = readLine()
            println()
            if (restartEverything3 == "yes") {
                cubicleProblem(1)
            }
            else
                exitProcess(0)
        } else if (sumCubicles <= 0 || uCubicles < 0 || dCubicles < 0) {
            println("You need to input more than 0 or a negative for the number of cubicles!")
            cubicleProblem(1)
        } else {

            println("Assuming that employees working in single cubicles are 100% productive.")
            println()
            val bestCase = sumCubicles
            val bestWFH = employees - bestCase

            //This is for the worst case scenario after the if
            val numPeopleAtWork = (2 * dCubicles) + uCubicles
            val doublePeople = dCubicles * 2
            val worstPeopleWHF = employees - (numPeopleAtWork)
            val worstPerProbability = ((uCubicles.toFloat() + (newAvgDoublePercentage * doublePeople)) / numPeopleAtWork.toFloat())
            val worstRealProbability = 100 * worstPerProbability

            //This is for the worst case scenario after the else
            val worstDoubleCubes = employees - sumCubicles
            val doublePeopleWorst = worstDoubleCubes * 2
            val worstSingleCubes = sumCubicles - worstDoubleCubes
            val newWorstNumPeople = doublePeopleWorst + worstSingleCubes
            val worstPreProb = ((worstSingleCubes.toFloat() + (newAvgDoublePercentage * doublePeopleWorst)) / newWorstNumPeople.toFloat())
            val worstProbability = 100 * worstPreProb

            println("-----------------------------------------------------------------------------------------------------" +
                    "---------------------")
            println("Best case scenario (100% average productivity): " +
                    "$bestCase single cubicles, 0 doubled cubicles and ${if (bestWFH == 1) "$bestWFH person" else "$bestWFH people"} working from home.\n" +
                    "-----------------------------------------------------------------------------------------------------" +
                    "---------------------")
            println()

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
                        "---------------------")
                println("Worst case scenario (${"%.2f".format(worstRealProbability)}% average productivity): " +
                        "$uCubicles single cubicles, $dCubicles doubled cubicles and " +
                        "${if (worstPeopleWHF == 1) "$worstPeopleWHF person" else "$worstPeopleWHF people"} people working from home.\n" +
                        "-----------------------------------------------------------------------------------------------------" +
                        "---------------------")
                println()

            } else {

                println("----------------------------------------------------------------------------------------------------" +
                        "---------------------")
                println("Worst case scenario (${"%.2f".format(worstProbability)}% average productivity): " +
                        "$worstSingleCubes single cubicles, $worstDoubleCubes doubled cubicles and 0 people working from home.\n" +
                        "----------------------------------------------------------------------------------------------------" +
                        "---------------------")
                println()
            }
            var boolean = true
            while (boolean == true) {
                println("What average productivity (in %) would you like?")
                println()
                val desiredProductivityPre = Integer.valueOf(readLine())
                val desiredProductivity = desiredProductivityPre.toFloat()
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

                    //from the top
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


                    //from the bottom

                    if ((2 * dCubicles) + uCubicles < employees) {

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
                    } else {
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


                    boolean = false
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