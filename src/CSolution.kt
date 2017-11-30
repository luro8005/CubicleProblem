fun main(args: Array<String>) {
    /*
    Create the following variables:
    uCubicles = cubicles that cannot be doubled
    dCubicles = cubicles that can be doubled
    employees = number of employees on a given day
    productivity = percentage of productivity you want
    utility = 100% if all cubicles are used
     */


    //Function to reduce amount of work done in each productivity scenario
    //It takes the total number of employees, the number of people that should have WFH and the total number of cubes
    //it returns a string with the number of double and single cubes for that specific day

    fun findNum4EachDay (totalNumPpl: Int, pplWFH: Int, totalCubes: Int ): String{
        var totalWorkingEmp = totalNumPpl - pplWFH
        var doubleCubes = 0
        var singleCubes = 0
        var totalCubesLeft = totalCubes
        while (totalWorkingEmp > totalCubesLeft){
            totalWorkingEmp -= 2
            doubleCubes += 1
            totalCubesLeft -= 1
        }
        if (totalWorkingEmp == totalCubesLeft){
            singleCubes = totalWorkingEmp
        }
        return("$singleCubes single cubicles, $doubleCubes double cubicles")
    }


    println()
    //First we ask how many cubicles can only be singles and how many can be doubles to get a total number of cubicles
    println("How many individual cubicles are there?")
    val uCubicles = Integer.valueOf(readLine())
    //var uCubicles : Int = 4

    println("How many cubicles can be doubled?")
    val dCubicles = Integer.valueOf(readLine())

    val sumOfCubes = uCubicles + dCubicles

    //Now we create all the productivity indices that we will want
    val productivity1  = 100.0f
    val productivity2  = 95.0f
    val productivity3  = 90.0f
    val productivity4  = 85.0f

    //Here we get the decimals for our productivity indexes
    val realProd1 = productivity1/100f
    val realProd2 = productivity2/100f
    val realProd3 = productivity3/100f
    val realProd4 = productivity4/100f

    //Sum the number of doublable cubicles with the number or non-doublable cubicles
    val sumCubicles = uCubicles + dCubicles

    //Ask what is the total number of employees
    println("How many employees total?")

    val employees = Integer.valueOf(readLine())

    //println("How many days are in next week?")

    val days = 5//Integer.valueOf(readLine())

    println("\n")

    //add code if there are less than 5 days
    val dayInt = 7
    val dayFloat = 7f

    if (employees <= 0) {
        println("With zero or negative employees there is no productivity and no utility!")
        return
    }

    //Get the number of cubicles that need to be singles in order to get desired productivity
    var simpleSolution1 = realProd1 * sumCubicles
    var simpleSolution2 = realProd2 * sumCubicles
    var simpleSolution3 = realProd3 * sumCubicles
    var simpleSolution4 = realProd4 * sumCubicles


    //if number of employees is less than or equal to the number of cubicles then there is no work to be done, print
    //100% productivity and the % of utility and return
    if (employees == sumCubicles){
        println("You have 100% productivity and 100% utility, no need for anyone to work from home!")
        return
    }
    //case where the number of employees is less than the number of cubicles
    else if(employees < sumCubicles){
        println("You have 100% productivity, but utility is only at ${(employees*100/sumCubicles)}%, no need for anyone" +
                " to work from home. \nYou need to have ${sumCubicles - employees} more employees to reach 100% utility " +
                "and productivity.")
        return
    }

    //the case where the number of cubicles is less than the amount of employees
    else {

        //*********************//
        //Use 100% productivity

        //create a whole number for monday and compare it with fridays in case of the mod being uneven
        val oneAndHalf = simpleSolution1.toInt()

        val oneAndHalfnew = oneAndHalf.toFloat() + 0.5

        if (simpleSolution1 >= oneAndHalfnew)
            simpleSolution1 += 1

        //Convert the value in simpleSolution1 to an int
        val newSingleCub1 = simpleSolution1.toInt()

        //Subtract the number of single cubicles from the total number of cubicles to get the number of cubicles that
        //need to be doubled
        val doubleCubicles1 = sumCubicles - newSingleCub1

        //Get the number of people that need to work from home
        var peopleWFH1 = employees - 2 * doubleCubicles1 - newSingleCub1

        //check for negatives of the number of people that need to work from home, switch to 0
        if (peopleWFH1 < 1)
            peopleWFH1 = 0

        //check whether the number of cubicles that have to be single is less than the number of cubicles that cannot
        //be doubles and return error statement
        if(newSingleCub1 < uCubicles){
            println("Unfortunately, $productivity1% productivity is unachievable because the number of cubicles that " +
                    "cannot be doubled is too high.")
            return
        }

        //create variable for the weekly count of people that should work from home
        val weeklyEmpWFH = peopleWFH1 * days

        println("In order to have $productivity1% average productivity, next week we need:\n" +
                "---------------------------------------------------------------\n" +
                "Monday:\n$newSingleCub1 individual cubicles, $doubleCubicles1 double cubicles and $peopleWFH1 employees WFH. \n"+
                "Tuesday:\n$newSingleCub1 individual cubicles, $doubleCubicles1 double cubicles and $peopleWFH1 employees WFH. \n" +
                "Wednesday:\n$newSingleCub1 individual cubicles, $doubleCubicles1 double cubicles and $peopleWFH1 employees WFH. \n" +
                "Thursday:\n$newSingleCub1 individual cubicles, $doubleCubicles1 double cubicles and $peopleWFH1 employees WFH. \n" +
                "Friday:\n$newSingleCub1 individual cubicles, $doubleCubicles1 double cubicles and $peopleWFH1 employees WFH. \n" +
                "Total number of weekly WFH is $weeklyEmpWFH.\n" +
                "---------------------------------------------------------------\n")
        /*

        println("To have $productivity1% productivity you need to have $newSingleCub1 single cubicles, $doubleCubicles1 douled " +
                    "cubicles and $peopleWFH1 people need to work from home. \n")
                    */

        //********************//
        //Use 95% productivity

        //create a whole number for monday and compare it with fridays in case of the mod being uneven
        val oneAndHalf2 = simpleSolution2.toInt()

        val oneAndHalfnew2 = oneAndHalf2.toFloat() + 0.5

        if (simpleSolution2 >= oneAndHalfnew2)
            simpleSolution2 += 1

        //Convert the value in simpleSolution2 to an int
        val newSingleCub2 = simpleSolution2.toInt()

        //Subtract the number of single cubicles from the total number of cubicles to get the number of cubicles that
        //need do be doubled
        val doubleCubicles2 = sumCubicles - newSingleCub2

        //Get the number of people that need to work from home
        var peopleWFH2 = employees - 2 * doubleCubicles2 - newSingleCub2

        //check for negatives of the number of people that need to work from home, switch to 0
        if (peopleWFH2 < 1)
            peopleWFH2 = 0

        val sumOfPeople2 = peopleWFH2 + (2*doubleCubicles2) + newSingleCub2

        //check whether the number of cubicles that have to be single is less than the number of cubicles that cannot
        //be doubles and return error statement
        if(newSingleCub2 < uCubicles){
            println("Unfortunately, $productivity2% productivity is unachievable because the number of cubicles that " +
                    "cannot be doubled is too high.")
            return
        }
        //make sure that the sum of the people in the cubicles doesn't exceed that of the employees
        else if (sumOfPeople2 > employees) {
            println("With the number of people you have the least you can do is the productivity above, assuming you want to " +
                    "use all your cubicles.")
            return
        }

        //create variable for the weekly count of people that should work from home
        val weeklyEmpWFH2 = peopleWFH2 * days

        val dayWFH2 = weeklyEmpWFH2 / dayFloat
        val modWFH2 = weeklyEmpWFH2 % dayInt
        val singleWFH2 = dayWFH2.toInt()
        val halfExWFH2 = (modWFH2/2f).toInt()
        val otherHalf2 = modWFH2 - halfExWFH2
        // testing errors
        //println("halfexWFH = $halfExWFH2 and otherHalf = $otherHalf2")

        //the weight each day has
        var mondayWFH2 = 2 * singleWFH2 + halfExWFH2
        var tuesdayWFH2 = singleWFH2
        var wednesdayWFH2 = singleWFH2
        var thursdayWFH2 = singleWFH2
        var fridayWFH2 = 2 * singleWFH2 + otherHalf2


        //create cases for when the number of people that should WFH surpasses the max and distribute the extra
        //people that should work from home among the other days
        while (mondayWFH2 > peopleWFH1){
            mondayWFH2 -= 1
            tuesdayWFH2 += 1

        }
        while (tuesdayWFH2 > peopleWFH1){
            tuesdayWFH2 -= 1
            wednesdayWFH2 += 1
        }
        while (wednesdayWFH2 > peopleWFH1){
            wednesdayWFH2 -= 1
            thursdayWFH2 += 1

        }
        while (fridayWFH2 > peopleWFH1 && tuesdayWFH2 < peopleWFH1){
            fridayWFH2 -= 1
            tuesdayWFH2 += 1
        }
        while (fridayWFH2 > peopleWFH1 && wednesdayWFH2 < peopleWFH1){
            fridayWFH2 -= 1
            wednesdayWFH2 += 1
        }
        while (fridayWFH2 > peopleWFH1 && thursdayWFH2 < peopleWFH1){
            fridayWFH2 -= 1
            thursdayWFH2 += 1
        }

        //val monFunc = findNum4EachDay(employees,mondayWFH2,sumOfCubes)

        println("In order to have $productivity2% average productivity, next week we need:\n" +
                "---------------------------------------------------------------\n" +
                "Monday:\n${findNum4EachDay(employees,mondayWFH2,sumOfCubes)} and $mondayWFH2 employees WFH.\n" +
                "Tuesday:\n${findNum4EachDay(employees,tuesdayWFH2,sumOfCubes)} and $tuesdayWFH2 employees WFH.\n" +
                "Wednesday:\n${findNum4EachDay(employees, wednesdayWFH2, sumOfCubes)} and $wednesdayWFH2 employees WFH.\n" +
                "Thursday:\n${findNum4EachDay(employees, thursdayWFH2, sumOfCubes)} and $thursdayWFH2 employees WFH.\n" +
                "Friday:\n${findNum4EachDay(employees, fridayWFH2, sumOfCubes)} and $fridayWFH2 employees WFH.\n" +
                "Total number of weekly WFH is $weeklyEmpWFH2.\n" +
                "---------------------------------------------------------------\n")

        /*
        println("To have $productivity2% productivity you need to have $newSingleCub2 single cubicles, $doubleCubicles2 douled " +
         "cubicles and $peopleWFH2 people need to work from home. \n")
        */

        //********************//
        //Use 90% productivity

        //create a whole number for monday and compare it with fridays in case of the mod being uneven
        val oneAndHalf3 = simpleSolution3.toInt()

        val oneAndHalfnew3 = oneAndHalf3.toFloat() + 0.5

        if (simpleSolution3 >= oneAndHalfnew3)
            simpleSolution3 += 1

        //Convert the value in simpleSolution3 to an int
        val newSingleCub3 = simpleSolution3.toInt()

        //Subtract the number of single cubicles from the total number of cubicles to get the number of cubicles that
        //need do be doubled
        val doubleCubicles3 = sumCubicles - newSingleCub3

        //Get the number of people that need to work from home
        var peopleWFH3 = employees - 2 * doubleCubicles3 - newSingleCub3

        //check for negatives of the number of people that need to work from home, switch to 0
        if (peopleWFH3 < 1)
            peopleWFH3 = 0

        val sumOfPeople3 = peopleWFH3 + (2*doubleCubicles3) + newSingleCub3

        //check whether the number of cubicles that have to be single is less than the number of cubicles that cannot
        //be doubles and return error statement
        if(newSingleCub3 < uCubicles){
            println("Unfortunately, $productivity3% productivity is unachievable because the number of cubicles that " +
                    "cannot be doubled is too high.")
            return
        }
        //make sure that the sum of the people in the cubicles doesn't exceed that of the employees
        else if (sumOfPeople3 > employees) {
            println("With the number of people you have the least you can do is the productivity above, assuming you want to" +
                    " use all your cubicles.")
            return
        }

        //create variable for the weekly count of people that should work from home
        val weeklyEmpWFH3 = peopleWFH3 * days

        val dayWFH3 = weeklyEmpWFH3 / 7f
        val modWFH3 = weeklyEmpWFH3 % 7
        val singleWFH3 = dayWFH3.toInt()
        val halfExWFH3 = (modWFH3/2f).toInt()
        val otherHalf3 = modWFH3 - halfExWFH3

        //the weight each day has
        var mondayWFH3 = 2 * singleWFH3 + halfExWFH3
        var tuesdayWFH3 = singleWFH3
        var wednesdayWFH3 = singleWFH3
        var thursdayWFH3 = singleWFH3
        var fridayWFH3 = 2 * singleWFH3 + otherHalf3

        //create cases for when the number of people that should WFH surpasses the max and distribute the extra
        //people that should work from home among the other days
        while (mondayWFH3 > peopleWFH1){
            mondayWFH3 -= 1
            tuesdayWFH3 += 1

        }
        while (tuesdayWFH3 > peopleWFH1){
            tuesdayWFH3 -= 1
            wednesdayWFH3 += 1
        }
        while (wednesdayWFH3 > peopleWFH1){
            wednesdayWFH3 -= 1
            thursdayWFH3 += 1

        }
        while (fridayWFH3 > peopleWFH1 && tuesdayWFH3 < peopleWFH1){
            fridayWFH3 -= 1
            tuesdayWFH3 += 1
        }
        while (fridayWFH3 > peopleWFH1 && wednesdayWFH3 < peopleWFH1){
            fridayWFH3 -= 1
            wednesdayWFH3 += 1
        }
        while (fridayWFH3 > peopleWFH1 && thursdayWFH3 < peopleWFH1){
            fridayWFH3 -= 1
            thursdayWFH3 += 1
        }


        println("In order to have $productivity3% average productivity, next week we need:\n" +
                "---------------------------------------------------------------\n" +
                "Monday:\n${findNum4EachDay(employees,mondayWFH3,sumOfCubes)} and $mondayWFH3 employees WFH.\n" +
                "Tuesday:\n${findNum4EachDay(employees,tuesdayWFH3,sumOfCubes)} and $tuesdayWFH3 employees WFH.\n" +
                "Wednesday:\n${findNum4EachDay(employees, wednesdayWFH3, sumOfCubes)} and $wednesdayWFH3 employees WFH.\n" +
                "Thursday:\n${findNum4EachDay(employees, thursdayWFH3, sumOfCubes)} and $thursdayWFH3 employees WFH.\n" +
                "Friday:\n${findNum4EachDay(employees, fridayWFH3, sumOfCubes)} and $fridayWFH3 employees WFH.\n" +
                "Total number of weekly WFH is $weeklyEmpWFH3.\n" +
                "---------------------------------------------------------------\n")



        /*
            println("To have $productivity3% productivity you need to have $newSingleCub3 single cubicles, $doubleCubicles3 douled " +
                    "cubicles and $peopleWFH3 people need to work from home. \n")
                    */


        //********************//
        //Use 85% productivity

        //create a whole number for monday and compare it with fridays in case of the mod being uneven
        val oneAndHalf4 = simpleSolution4.toInt()

        val oneAndHalfnew4 = oneAndHalf4.toFloat() + 0.5

        if (simpleSolution4 >= oneAndHalfnew4)
            simpleSolution4 += 1


        //Convert the value in simpleSolution4 to an int
        val newSingleCub4 = simpleSolution4.toInt()

        //Subtract the number of single cubicles from the total number of cubicles to get the number of cubicles that
        //need do be doubled
        val doubleCubicles4 = sumCubicles - newSingleCub4

        //Get the number of people that need to work from home
        var peopleWFH4 = employees - 2 * doubleCubicles4 - newSingleCub4

        //check for negatives of the number of people that need to work from home, switch to 0
        if (peopleWFH4 < 1)
            peopleWFH4 = 0

        val sumOfPeople4 = peopleWFH4 + (2*doubleCubicles4) + newSingleCub4

        //check whether the number of cubicles that have to be single is less than the number of cubicles that cannot
        //be doubles and return error statement
        if(newSingleCub4 < uCubicles){
            println("Unfortunately, $productivity4% productivity is unachievable because the number of cubicles that " +
                    "cannot be doubled is too high.")
            return
        }
        //make sure that the sum of the people in the cubicles doesn't exceed that of the employees
        else if (sumOfPeople4 > employees){
            println("With the number of people you have the least you can do is the productivity above, assuming you want" +
                    " to use all your cubicles.")
            return
        }

        //create variable for the weekly count of people that should work from home
        val weeklyEmpWFH4 = peopleWFH4 * days

        val dayWFH4 = weeklyEmpWFH4 / 7f
        val modWFH4 = weeklyEmpWFH4 % 7
        val singleWFH4 = dayWFH4.toInt()
        val halfExWFH4 = (modWFH4/2f).toInt()
        val otherHalf4 = modWFH4 - halfExWFH4

        //the weight each day has
        var mondayWFH4 = 2 * singleWFH4 + halfExWFH4
        var tuesdayWFH4 = singleWFH4
        var wednesdayWFH4 = singleWFH4
        var thursdayWFH4 = singleWFH4
        var fridayWFH4 = 2 * singleWFH4 + otherHalf4


        //create cases for when the number of people that should WFH surpasses the max and distribute the extra
        //people that should work from home among the other days
        while (mondayWFH4 > peopleWFH1){
            mondayWFH4 -= 1
            tuesdayWFH4 += 1

        }
        while (tuesdayWFH4 > peopleWFH1){
            tuesdayWFH4 -= 1
            wednesdayWFH4 += 1
        }
        while (wednesdayWFH4 > peopleWFH1){
            wednesdayWFH4 -= 1
            thursdayWFH4 += 1

        }
        while (fridayWFH4 > peopleWFH1 && tuesdayWFH4 < peopleWFH1){
            fridayWFH4 -= 1
            tuesdayWFH4 += 1
        }
        while (fridayWFH4 > peopleWFH1 && wednesdayWFH4 < peopleWFH1){
            fridayWFH4 -= 1
            wednesdayWFH4 += 1
        }
        while (fridayWFH3 > peopleWFH1 && thursdayWFH4 < peopleWFH1){
            fridayWFH4 -= 1
            thursdayWFH4 += 1
        }




        println("In order to have $productivity4% average productivity, next week we need:\n" +
                "---------------------------------------------------------------\n" +
                "Monday:\n${findNum4EachDay(employees,mondayWFH4,sumOfCubes)} and $mondayWFH4 employees WFH.\n" +
                "Tuesday:\n${findNum4EachDay(employees,tuesdayWFH4,sumOfCubes)} and $tuesdayWFH4 employees WFH.\n" +
                "Wednesday:\n${findNum4EachDay(employees, wednesdayWFH4, sumOfCubes)} and $wednesdayWFH4 employees WFH.\n" +
                "Thursday:\n${findNum4EachDay(employees, thursdayWFH4, sumOfCubes)} and $thursdayWFH4 employees WFH.\n" +
                "Friday:\n${findNum4EachDay(employees, fridayWFH4, sumOfCubes)} and $fridayWFH4 employees WFH.\n" +
                "Total number of weekly WFH is $weeklyEmpWFH4.\n" +
                "---------------------------------------------------------------\n")

        /*
            println("To have $productivity4% productivity you need to have $newSingleCub4 single cubicles, $doubleCubicles4 douled " +
                    "cubicles and $peopleWFH4 people need to work from home. \n")
        */

    }
}


//what productivity do you want?
//"on average x number of ppl working from the office and y number of ppl working from home


///build sanity check building rules to verify that the lineups are good
//lineups are created from controllers. its metadata
//ams box

/*1. get lineups
        2.add rules (generic accross any box, and rules that are specific to a box)

        learn xml
        need to understand lineups
        CL-MS
*/


