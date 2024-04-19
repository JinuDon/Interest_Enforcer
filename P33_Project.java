import java.util.Scanner;

/*Serious questions to consider:
 * Why do we have the FAFSA section?
 * * * How does the FAFSA section impact the loan story line? Make sure to mention that throughout the steps (I can do that if ik y)
 * * * Can't we shorten it by saying we'll take SOME parts of the FAFSA, and not make it as if we're going to help users understand how to fill out FAFSA completely?
 * * * OR, we can just ask the income/assets qs and say these are taken from your FAFSA, which is blah blah, at the end of the 3 qs?
 * How did the debt form? Yes, ik we did calculations, but the user doesn't know about any of that. We aren't making a loans calculator... 
 * * * so we should explain "Q1 + Q2 lessened your debt by this much $, and so now you're debt is $"
 * Why is the actual loan story line only 1 question? If I was the player, tbh I still wouldn't know anything about loans after playing
 * * * We need some sort of scenario that can help users understand things that are complicated. 
 * * * For example, how much interest will I accumulate in the future? If necessary, we can separate loan options to make it less realistic and more informative
 * * * Here's some things that many people don't know about (can just work on like 2 or 3 and call it a day tbh, I can help, will need your research):
 * 			Grace period
 * 			Repayment strategies
 * 			Cosigners - how parents or cosigners credits can be affected by loans as well, everyone is equally responsible to pay back!
 *			Specific loan terms
 * 			Private vs. Federal Loans - kind of specifically tell how they're different, the player shouldn't have to piece things together
 * 			Credit impact
 * 			Interest accumulation
 * 			Loan forgiveness
 * 
Can do's:
 * explain what gross income is, explain how the choice made impacts LOANS. So what if it will give me some financial aid?
 * what are assets? How does that impact loans??
 * make qs more entertaining. For example,
 * * * instead of saying choose an income: $0, 35k to 70k, etc.; say choose an option:
 * 			1) you live off one meal of bread and water, but you're content with your life. (POOR - $0)
 * 			2) You live a humble life. You may have a 2-story house, but you spend your money wisely. (MIDDLE CLASS - 35k to 70k)
 * 			3) You fly to New York when you crave their pizza on your private jet. (RICH - 140k or above)
 * */

// Roughly Complete
public class P33_Project {
	static boolean failure=false;
	static String name;
	static Double money=0.0d;
	static Scanner s = new Scanner(System.in);
	static Double debt;
	static Double fafsAmount;
	static boolean[] noRepeat = {false,false,false,false};
	static String userInput;
	static boolean independent = false;
	static boolean[] x = {true,true,true};
	public static void main(String[] args) {
		System.out.println(
				"Hello proprietor of financial confusion and welcome! You are about to embark on a journey that will leave your brain and bank account full. "
				+ "This game will walk you through your choice of a financial journey. You’ll come across many obstacles in your journey, but if you persevere, you’ll be able to do the same on your own and be prepared for your future. \nWithout further ado, let the game begin!\r\n");
		System.out.println("What's your name?\r\n");
		name = s.nextLine();
		System.out.println();
		
//************************************************************************************************
		
		introduction();
		System.out.println("You have now completed your character background. Let's begin the game!\n\n"
				+ "Press enter to continue when ready.");
		s.nextLine();
		System.out.println("\n");
		
		FAFSA();
		System.out.println("\nYou have now completed the FAFSA section. Your college sends you financial aid offers like federal student loans based on your financial situation.\n\n"
				+ "Press enter to continue when ready.");
		s.nextLine();
		System.out.println("\n");
		
		loan();
		end();
		System.out.println("\nIt's been quite the journey, from the reality of your financial status hitting to choosing the right loan to help ease your debts. "
				+ "You decide to take a breather and sip on some lemonade while sitting on your patio swing. It is simply a moment of nature and peace...\n\n"
				+ "Press enter to continue when ready.");
		s.nextLine();
		System.out.println("\n");
		
		tax();
		
	}
	public static void introduction() {
		String[] introQuestion = {
				"What college are you planning on attending?",
				"Missississippi Institute of Teknology",
				"University of Yurmoughm",
				"Richy McRicherson's Academy",
				"CollegeTown Community College"
				};
		String[] activityQuestion = {
				"Throughout the first year, you will have the opportunity to participate in many activities. Which will you choose?",
				"Internship at Goggle",
				"Part-time job at McNaldo's",
				"P34 (school club)",
				"Gambling"
				};
		String[] moreQuestion = {
				"What else might you want to have, before you go?",
				"School Supplies",
				"Snookie Wookie (pet)",
				"Red Rover (car)",
				"Mojo Dojo Casa House (apartment)"
				};
		double[] introMoney = {-43000,-23000,-15000,0};
		double[] activityMoney = {10000,3000,0,-1500};
		double[] moreMoney = {0,-1500,-10000,-30000};
		String[] introResponses = {
				"You have chosen the Missississippi Institute of Teknology. A great choice in terms of education, but very expensive. You got a scholarship for 37 thousand dollars, a great discount.", 
				"You have chosen the University of Yurmoughm. A fairly reputable university, it will cost a pretty penny. You got a scholarship for 27 thousand dollars, a solid discount.",
				"You have chosen Richy McRicherson. Not too expensive, despite its name, but it will still cost you. You got a scholarship for 5 thousand dollars, a reasonable discount.", 
				"You have chosen CollegeTown Community College. A respectable and financially conscious choice, as you earned a full ride and will not be paying anything."
				};
		String[] activityResponses = {
				"You have chosen to apply for an internship at Goggle. This is a big deal, as Goggle is a prestigious tech company.",
				"You have chosen to get a part-time job at McNaldo's. It pays, but not much.",
				"You have chosen to join the P34 club. This is a free-to-join club.", 
				"You have chosen to gamble. A stupid choice, as you are now down money."
				}; 
		String[] moreResponses = {
				"You have chosen to get school supplies. These come free with your tuition payment, and cost you nothing.", 
				"You have chosen Snookie Wookie. While giving you the joy of companionship, she will cost you some money throughout the year.",
				"You have chosen to buy the Red Rover. Now you can get around, which may or may not be useful, but it will cost quite a bit.",
				"You have chosen to rent an apartment at the Mojo Dojo Casa House. Rent will cost you a lot."
				}; 
		monetaryQuestion(introQuestion, introMoney, introResponses,true,noRepeat);
		monetaryQuestion(activityQuestion, activityMoney, activityResponses,true,noRepeat);
		monetaryQuestion(moreQuestion, moreMoney, moreResponses,true,noRepeat);
		
	}
	public static void monetaryQuestion(	//Generic question prompter with 4 questions, responses, and associated balance changes.
			String [] qAndA,//Array storing the question to be asked and all valid answers
			double[] moneyResult,//Array storing the change in money after each choice
			String[] responses,//Array storing the responses for each user choice
			boolean prompt,//If you want to print the value of money
			boolean[] repeat //If you want to repeat the question after each response
										) {
		boolean looping = true;
		while (looping) {//keep going until they enter valid input
//			System.out.print("Alright, "+name+", "); //some way to address the user when asking question.
			System.out.println();
			System.out.println(qAndA[0]);
			System.out.println();
			for(int i = 1; i<qAndA.length;i++) {
				System.out.println("Option "+i+": "+qAndA[i]);//print out the specified question and answers
			}
			userInput = s.nextLine();//look for answer
			System.out.println();
			switch (userInput){//depending on answer, different result
				case "1":{
					money+=moneyResult[0];
					System.out.println(responses[0]);
					looping = repeat[0];
					break;
				}
				case "2":{
					money+=moneyResult[1];
					System.out.println(responses[1]);
					looping = repeat[1];
					break;
				}
				case "3":{
					money+=moneyResult[2];
					System.out.println(responses[2]);
					looping = repeat[2];
					break;
				}
				case "4":{
					money+=moneyResult[3];
					System.out.println(responses[3]);
					looping = repeat[3];
					break;
				}
				default: 
					System.out.println("Please enter the number of your choice.\r\n");
					break;
			}
		}
		if(prompt)System.out.println("Your current balance after choice: "+money+"\r\n");
	}
	public static void nonMonetaryQuestion(	//Generic question prompter with 4 questions, responses, and associated balance changes.
			String [] qAndA,//Array storing the question to be asked and all valid answers
			String[] responses,//Array storing the responses for each user choice
			boolean[] repeat //If you want to repeat the question after each response
											){
		while (true) {//keep going until they enter valid input
//			System.out.print("Alright, "+name+", "); //some way to address the user when asking question.
			System.out.println(qAndA[0]);
			for(int i = 1; i<qAndA.length;i++) {
				System.out.println("Option "+i+": "+qAndA[i]);//print out the specified question and answers
			}
			userInput = s.nextLine();//look for answer
			System.out.println();
			switch (userInput){//depending on answer, different result
				case "1":{
					System.out.println(responses[0]);
					return;
				}
				case "2":{
					System.out.println(responses[1]);
					return;
				}
				case "3":{					
					System.out.println(responses[2]);
					return;
				}
				case "4":{
					System.out.println(responses[3]);
					return;
				}
				default: 
					
//********************************************************************************
					
					System.out.println(responses[Integer.parseInt(userInput)]); //this will catch all errors!!!
					return;
			}
		}
	}
	public static void FAFSA() {
		debt = money;
		money=0.0d;
		
//*********************************************************************************		
		
		System.out.println(
				"Let us begin with filling out some of the Free Application for Federal Student Aid (FAFSA) form that has substantial weight. This form is recommended to be filled "
				+ "out because it gives you access to the largest source of financial aid to help you pay for college or career "
				+ "school. This information determines your eligibility for state and school aid.\n\n"
		//add how this even impacts loans.
		//changed some statements, need to add some stuff still
                + "When filling out the real FAFSA for financial aid, you will be asked many personal identifiable questions, such as student homelessness, address, and SSN . As this is simply a simulation made "
                + "for the purpose of supplementing your financial literacy, this section is NOT an accurate representation of what may appear on the FAFSA.\r\n");
		
		independent = false;
		
        String[] dependencyQuestion = {
                "Choose an option:",
                "You pay for your own ice-cream.",
                //"Independent (24 years or older by Dec. 31, married, a graduate or professional student, veteran, member of the armed forces, an orphan, a ward of the court, \nor someone with legal dependents other than a spouse.)",
                "Your mom still pays for your ice-cream."
        };
        String[] dependencyResponse = {
                "Let's keep the ice-cream aside for now. You may need it in the future.\nAnywho, you are an independent. This means you are either 24 years or older by Dec. 31, married, a graduate or professional student, veteran, member of the armed forces, an orphan, a ward of the court, \\nor someone with legal dependents other than a spouse. You will not have to provide family income information, but just you and your spouse's if applicable. Independent students are eligible for higher loan borrowing amounts, but not the Parent PLUS loan",
                "Let's keep the ice-cream aside for now. You may need it in the future.\nAnywho, you are a dependent, so you will have to provide information regarding family income, assets, and any other relevant financial details",
        };
	String[] annualIncomeQuestion = {
		"Choose an option to determine an income-related approximation:", //family's approximate gross annual income
		"Your daily schedule is something along the lines of waking up, freshening up, doing some exercises, lining up to get food stamps from the government, going to school, and so on.", //this is kind of harsh....15k to 35k (low-income)
		"Your mom tells you she's getting some donuts from Dousing Donuts, but you think she's lying because you can't afford it.",	//35k to 70k (lower-middle)
		"Your mom buys Bytecoin from RedRobinHood.",	//70k to 140k (upper-middle)
		"You party when you feel like it, go to another country when you want to, and throw tantrums at your parents when they don't give you what you want."	//140k or above
	};
	double[] annualIncomeAverage = {
                25,
                52.5,
                105.5,
                200,
        };
        String[] annualIncomeResponse = {
                "Your family’s income is 15k to 35k which is considered to be low income. This may yield considerable financial aid.",
                "Your family’s income is 35 to 70k which is considered to be lower-middle class. This may yield appreciable financial aid.",
                "Your family’s income is 70k to 140k which is considered to be upper-middle class. This may yield some financial aid.",
                "Your family’s income is above 140k, which is relatively wealthy. This may yield minimal financial aid.."
        };
        String[] personalIncomeQuestion = {
		"Choose an option to determine another income-related approximation:",
		"You are an assistant manager at a chicken restaurant called JFC (John F. Chicken)",	//35k to 70k
		"Everyone in your life is toxic, but you never realize it.",	//15k to 35k
		"You live by the motto 'you're better than everyone and no one's better than you.'", //0k (low-income)
		"You fix your bed every morning, accept your failures, and make wise decisions."	//70k or above
	};
	double[] personalIncomeAverage = {
                7,
                25,
                52.5,
                100,
        };
        String [] personalIncomeResponse = {
        		"You have a steady job. Your income is 35k to 70k, which may yield some financial aid",
        		"You made a decent amount of money, but the people around you prevent you from progessing further. Your income is 15k to 35k, which may yield appreciable financial aid.",
        		"You have a horrible personality, and no one wants to hire you. Your income is 0k, so you do not make any money. On the bright side, this may yield considerable financial aid.",
                "You know what you're doing and have a healthy lifestyle. Your income is greater than 70k, but this may yield minimal, if any, financial aid."
        };
        String[] assetEstimatedPriceQuestion = {
                "Choose a type of asset.",
                "Your greatest desire in life is to buy the one and only HOPOW Luxury Smart Toilet with a Heated Bidet, Dryer, and Warm Water. This costs $1,000, so you start a savings for it.", //0 to 1k
                "You invest in the common stock 'Yankee Express,' and have some assets from it.", //5k to 10k
                "You always dreamed of raising your children amongst the scent of cows and chickens, gazing at them lovingly while they race across a wide green field. Although there's currently no animals and only dry weeds, you have three acres of farmland in the US.", //15k to 30k
                "You have a very rare baseball card." //50k and above
        };
	double[] assetEstimatedPriceAverage = {
                0.5,
                7.5,
                22.5,
                30,
        };
	String[] assetEstimatedPriceResponse = {
		"Reportable assets include your money in cash, savings, checking accounts, businesses or farms, investement farms, or other investements such as real estate (other than primary residence), stocks, bonds, and certificates of deposit. (Not including primary residence, life insurance value, and retirement plans). Your reported assets have an estimated price of $0 to $1k",
		"Reportable assets include your money in cash, savings, checking accounts, businesses or farms, investement farms, or other investements such as real estate (other than primary residence), stocks, bonds, and certificates of deposit. (Not including primary residence, life insurance value, and retirement plans). Your reported assets have an estimated price of $5k to $10k",
		"Reportable assets include your money in cash, savings, checking accounts, businesses or farms, investement farms, or other investements such as real estate (other than primary residence), stocks, bonds, and certificates of deposit. (Not including primary residence, life insurance value, and retirement plans). Your reported assets have an estimated price of $15k to $30k",
		"Reportable assets include your money in cash, savings, checking accounts, businesses or farms, investement farms, or other investements such as real estate (other than primary residence), stocks, bonds, and certificates of deposit. (Not including primary residence, life insurance value, and retirement plans). Your reported assets have an estimated price of more than 50k",
	};
	
	
	boolean success = false;
		
//**********************************************************
		
	while (!success) { //added statements to try instead of outside of it
	    try {
		nonMonetaryQuestion(dependencyQuestion, dependencyResponse, noRepeat);
		success = true; 
		if (userInput.equals("2")) independent = false; //changed from == to .EQUALS (OMG Y Y Y DIDN'T YOU DO THAT?!?!?!)
			else if(userInput.equals("1")) independent = true; //this way it'll properly make independent = to true
		monetaryQuestion(annualIncomeQuestion,annualIncomeAverage,annualIncomeResponse,false,noRepeat);
			monetaryQuestion(personalIncomeQuestion,personalIncomeAverage,personalIncomeResponse,false,noRepeat);
			monetaryQuestion(assetEstimatedPriceQuestion,assetEstimatedPriceAverage,assetEstimatedPriceResponse,false,noRepeat);
			fafsAmount = money;
			if(25<=fafsAmount&&fafsAmount<45) {
				debt+=15000;
				System.out.println("\nFAFSA RESULT: Due to your financial need, as you are low-income, you are given a grant for $15,000 of financial aid. ");
			}else if(45<=fafsAmount&&fafsAmount<85) {
				debt+=10000;
				System.out.println("\nFAFSA RESULT: Due to your financial need, as you are lower-middle class income, you are given a grant for $10,000 of financial aid. ");
			}else if(85<=fafsAmount&&fafsAmount<180) {
				debt+=8500;
				System.out.println("\nFAFSA RESULT: Due to your financial need, as you are upper-middle class income, you are given a grant for $8,500 of financial aid. ");
			}else if(180<=fafsAmount) {
				System.out.println("\nFAFSA RESULT: Due to your financial need, as you are relatively wealthy income, you are given a grant for $6,500 of financial aid. ");
				debt+=6500;
			}
	    } catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("Invalid input. If you are an independent, please type 1. If you are a dependent, please type 2..");
	    } catch (NumberFormatException nfe) { //added exception so it catches errors
		System.out.println("Please enter the number of your choice.");
	    }
	}		
}
	public static void loan() {
		
//********************************************************************************************
		
		//need to add loan story line beginnning
		System.out.println("Beginning of loan storyline**********************************************\n\n"
				+ "This educational expedition will both influence your professional capacity substantially, and significantly impact your financial landscape. "
				+ "Student loans may be daunting and \nintimidating to undertake without help and proper preparation, so let's simplify the intricacies to choose the best loan\n"
				+ "type that will benefit your financial and educational \njourney.\r\n");
		
        String[] loanTypeQuestion = {
                "What type of loan do you want to take out?",
                "FEDERAL DIRECT SUBSIDIZED || Based on financial need || Government covers interest while you're in school || Lower loan limit",
                "FEDERAL DIRECT UNSUBSIDIZED || Not based on financial need || Not subsidized - Government does not cover interest || Higher loan limit",
                "PRIVATE STUDENT LOAN FROM BANK || Yields possible benefits if cosigner (person signing on the loan along with you) has good credit || No income-driven repayment (no extracting a % from income obtained || No subsidies available)",
                "PARENT DIRECT PLUS || Fixed interest rate (interest rate will not change as time goes on. Stays the same as when loan signed.) || Not subsidized || Interest may accrue while in school",    
        };

		String[] loanTypeResponse = {
				"This is a loan only available to undergraduates. This type of loan is based on financial need."
				+"Additionally, the government pays the interest while you're in school, so interest doesn't accrue. Although, there is a lower loan limit, "
				+"so you have to be responsible with how much you borrow.",
				"This loan, unlike the federal direct subsidized loan, is available to both undergraduates and graduates. This is not a need-based loan. "
				+ "You will not receive any subsidies and the government does not cover interest while you're in school, but you start paying 6 months after leaving "
				+ "college or drop below half-time enrollment (Enrollment as a student in your college). Furthermore, there is a higher loan limit, so it is important to note that you must be responsible with"
				+ " how much you borrow.",
				"This option may yield you being 'rewarded' through a decrease in interest rate if your cosigner (person signing on the loan along with you) has high credit. "
				+ "Higher borrowing limit is possible compared to a federal direct loan, but there is no federal forgiveness, no income-driven repayment plans, "
				+ "and no federal subsidies available.",
				"A Parent Direct PLUS loan is a federal loan that graduates or professional degree students or parents of dependent undergraduate students can use "
				+ "with a fixed interest rate. This loan is not subsidized, which means that interest can accrue while the student is enrolled."
		};
		String[] loanTypeDenied = {
				"You have already taken a Federal Direct Subsidized, try another type.\n",
				"You have already taken a Federal Direct Unsubsidized, try another type.\n",
				"",
				"You have already taken a Parent Direct PLUS Loan, try another type.\n",
		};		
		Double oldDebt = debt;
		while (debt<0) {
			System.out.println("\nAs of now, you're total debt is " + debt + " dollars.\n");
		
//			System.out.print("Alright, "+name+", "); //some way to address the user when asking question.
			System.out.println(loanTypeQuestion[0]);
			for(int i = 1; i<5;i++) {
				System.out.println("Option "+i+": "+loanTypeQuestion[i]);//print out the specified question and answers
			}
			
			String userInput = s.nextLine();//look for answer
			System.out.println();
			switch (userInput){//depending on answer, different result
				case "1":
					if(x[0]) {
						System.out.println(loanTypeResponse[0]);
						if(independent) {
							debt += 9500;
						}else debt += 5500;
						x[0] = false;
						break;
					}else {
						System.out.println(loanTypeResponse[0]+"\n");
						System.out.println(loanTypeDenied[0]);
						break;
					}
				case "2":
					if(x[1]) {
						System.out.println(loanTypeResponse[1]);
						if(independent) {
							debt += 9500;
						}else debt += 5500;
						x[1] = false;
						break;
					}else {
						System.out.println(loanTypeResponse[1]+"\n");
						System.out.println(loanTypeDenied[1]);
						break;
					}
				case "3":					
					System.out.println(loanTypeResponse[2]);
					debt += Math.abs(debt);
					failure = true;
					break;
				case "4":
					if(x[2]) {
						System.out.println(loanTypeResponse[3]);
						if(independent) {
							System.out.println("You claimed to be an independent, so your parents can't take out a loan for you.");
						}else debt += 15000;
						x[2] = false;
						break;
					}else {
						System.out.println(loanTypeResponse[3]+"\n");
						System.out.println(loanTypeDenied[3]);
						break;
					}
				default: 
					System.out.println("Please enter the number of your choice.");
					break;
				}
			}
		}
	public static void end() {
		
//*********************************************************************************************
	String [] loanConclusion = {
		"\nDue to your relatively good choices and possibly high financial need, you have been able to acquire all of your necessary student loans without having to take out a personal bank loan. Therefore, you are eligible for an income-driven repayment plan, which will only extract a percentage of your total income so that repayment is manageable, but that's a story for another day. For now, you lay down on a hammock, sippin' on a Pina Colada, listening to 'Escape: The Pina Colada Song.",
		"Due to your poor choices and possibly minimal financial need, you have been able to acquire all of your necessary loans by resorting to the private student loan from the bank. You will not be able  to choose an income-driven repayment plan for all of the loans, as private student loan companies do not offer income-driven repayment options for borrowers. Therefore, a percentage of your income will not be extracted, but you will have to pay fixed monthly payments through a standard repayment plan, but that's a story for another day. For now, you lay down on a hammock, sippin' on a Pina Colada, listening to 'Escape: The Pina Colada Song.'"
	};
	
	
	if(failure == false){
	    System.out.println(loanConclusion[0]);
	} else{
	    System.out.println(loanConclusion[1]);
	}
	}
	public static void tax() {
		Scanner scan = new Scanner(System.in);
		boolean looping = true;
		
		System.out.println("Another beautiful day dawns upon you in April. The blooming flowers sway in the pleasant breeze and the Spring warmth is finally starting to settle in. "
				+ "\nYou close your eyes and take a deep breath as you embrace the soft sunlight and vibrant colors surrounding you. You can hear the tranquil sound of "
				+ "\ngentle water splashing in the shimmering white stone fountain as the birds chirp away in perfect harmony. Ah, April… A truly enchanting month…or so "
				+ "\nyou would think. Unfortunately, April is no longer the peaceful month you remember from your oh so youthful days. Alas, it has taken a new role, one "
				+ "\nfilled with dread and responsibility! Now, you must prepare yourself to face the daunting task of tax preparation. But fear not, oh proprietor of financial "
				+ "\nconfusion, as this game will help you throughout the journey!");
		
		System.out.println("\nIf you don't want to file your own taxes, you may hire someone to do them for you. Do you want to hire someone? (Enter 'y' for yes, 'n' for no)");
				//decrease money
		String skip = scan.nextLine();
		if((skip.toLowerCase()).equals("y")) {
			System.out.println("\nYou have chosen to hire someone to file your taxes for you.");
			while (looping) {
				System.out.println("Select an option for the person/organization you'd like to hire for the job: \n"
					+ "Option 1: A Certified Public Accountant\n"
					+ "Option 2: An Enrolled Agent\n"
					+ "Option 3: A shady local tax consultant you found on Facebook (Meta)\n"
					+ "Option 4: A tax preparation company");
				String hire = scan.nextLine();
				System.out.println("");
				switch (hire){
				case "1":{
					System.out.println("You have chosen to hire a Certified Public Accountant (CPA). A CPA is a licensed professional and will assess your financial situation, collect"
							+ "\nnecessary information, and accurately complete your tax forms.");
					System.out.println("\nThis will cost you some money. As they are professionals, you will pay $250 for a CPA.");
					debt-=250;
					looping = false;
					break;
				}
				case "2":{
					System.out.println("You have chosen to hire an Enrolled Agent (EA). An EA is a tax professional licensed by the Internal Revenue Service (IRS) and empowered by"
							+ "\nthe U.S. Department of the Treasury. They will represent you as a taxpayer before the IRS and accurately complete your tax forms.");
					System.out.println("\nThis will cost you some money. As enrolled agents are more on the costly side, you will pay $300.");
					debt-=300;
					looping = false;
					break;
				}
				case "3":{
					System.out.println("You have chosen to hire a shady local tax consultant you found on Facebook (Meta). That was a great choice... no really. It turns out the"
							+ "\nconsultant you had chosen was a completely incompetent scammer. They filed your taxes incorrectly and ran off with your money. Now, you must pay a large fine"
							+ "\nas a penalty (determined by the IRS), and have been summoned for an IRS audit, where the IRS will thoroughly review your financial records and tax documents.");
					System.out.println("\nThis poor choice of yours will cost you a lot of money. You not only payed an excessive $500 to the scammer, but are also fined an additional $200"
							+ "\nfrom the IRS, as this is 20% of the amount you incorrectly incorrectly claimed. You also have to return the money you incorrectly claimed.");
					debt-=700;
					looping = false;
					break;
				}
				case "4":{
					System.out.println("You have chosen to hire a tax preperation company. You hire a licensed professional from 'S&E Cube,' an authentic tax preparation company."
							+ "\nThey will guide you through the tax preparation process and accurately complete your tax forms.");
					System.out.println("\nThis will cost you some money. Since S&E Cube is more on the affordable side, it'll cost you $100.");
					debt-=100;
					looping = false;
					break;
				}
				default: 
					System.out.println("Please enter the option number of your choice.");
					break;
				}
			}
			
			System.out.println("\nPress enter to continue when ready.");
			scan.nextLine();
			
			System.out.println("\nThere are various resources available for you if you would like to hire someone to file your taxes for you, from lawyers to licensed professionals to online resources."
					+ "\n\n"
					+ "- Contact these resources as early as possible and well before the tax filing deadline. They will need some time to guide you through the process and gather"
					+ "\nany information needed from you. \n"
					+ "- Prices will vary depending on who you choose. Online resources are usually cheaper, but in-person may be more easier to work with. If your taxes include higher"
					+ "\ncomplexities, more hours, or additional services, the prices may be higher.\n"
					+ "- Most importantly, hire someone professional. Check if they have the right qualifications, experience, and reputation. Ask friends, family, or online reviews if"
					+ "\nnecessary. Don't be hasty when choosing someone, and definitely avoid people like option 3."
					+ "\n\nRemember, at the end of the day, you are responsible for your own taxes, regardless of who files them. :)");
		}
		else {
		
		System.out.println("\nBefore we begin, would you like to get a job this year? (Enter 'y' for yes, 'n' for no)");
		String job = scan.nextLine();
		
		if((job.toLowerCase()).equals("y")) FormW4();
		
		System.out.println("\n\nSince it's April, it's time you start filing your federal individual income tax returns.");
		
		while (looping) {
			
			System.out.println("\nChoose an option to determine which tax return you'll need to fill out:\n" +
					"Option 1: You are extremely allergic to all animals, but you really wanted a pet. Therefore, you went to your local adoption center and took the first child you saw.\n" +
					"Option 2: You have no game, no passion, and no drive. You are destined to be lonely until your college years are over. However, you tell everyone you're having the time of your life.\n");
			String taxReturn = scan.nextLine();
			String redo;
			System.out.println(" ");
			switch (taxReturn){
			case "1":{
				System.out.println("You have dependents and need to include them on your taxes, meaning you need to file a 1040A form.\n\n");
				System.out.println("Would you like to re-do the question to choose another option? (Enter 'y' for yes, 'n' for no)");
				redo = scan.nextLine();
				if((redo.toLowerCase()).equals("y"))looping = true;
				else {
					looping = false;
					Form1040A();
				}
				break;
			}
			case "2":{
				System.out.println("You are unfortunately single and therefore will need to file a 1040EZ form.\n\n");
				System.out.println("Would you like to re-do the question to choose another option? (Enter 'y' for yes, 'n' for no)");
				redo = scan.nextLine();
				if((redo.toLowerCase()).equals("y"))looping = true;
				else {
					looping = false;
					Form1040EZ();
				}
				break;
			}
			default: 
				System.out.println("Please enter the option number of your choice.");
				break;
			}
		}
	}
		System.out.println("\n\nYou have now completed the tax storyline! Let's jump into the future and see what happens next year with your loans. Good luck on your future endeavors!");
	}
	public static void Form1040EZ() {
		System.out.println("There are a few different steps to fill out the 1040EZ tax form. " 
				+ "The first is to provide some personal information including your first and \nlast name and middle initial, " 
				+ "your social security number, your spouse's information, and your address." + 
				"\n\nThe next step is to provide income information." 
				+ "Much of the data needed for this step was not determined in previous portions of the game, so \nthis is simply to " +
				"tell you what you would need when filling this out in your real life." + "\n\nIf you are employed, "
				+ "your employer will give you a W-2 which will tell you how much you have earned in the year. " 
				+ "You take this number, your \ntaxable interest, and any unemployment compensation you have made to " 
				+ "calculate your adjusted gross income." + "\n\nThat was a lot of words, so allow us to break some of those terms down." + 
				"\n\nTaxable interest is the income that was made off of bank accounts that give interest, and lending money," 
				+ " as well as other forms of income \nthrough things of a similar nature. Unemployment compensation is the money that is " 
				+ "received from the government after filing for unemployment \nwhen one loses their job." + "\n\nYou will then use your " + ""
				+ "gross income to determine your taxable income." + "\n\nThat's one section down as we move into calculating if you " 
				+ "get a tax refund or if you owe money." + "\n\nThe numbers that determine if you recieve a refund or not are your total " +
				"payments and credits and total " + "\n\nTotal payments and credits is the amount of money that you have paid throughout "
				+ "the year in tax credits. This number is subtracted \nfrom the total tax because it is taxes that were already paid." + 
				"\n\nThe total tax is the total amount of taxes that you owe. If this number is greater than the total payments and credits, " 
				+ "then the amount you \nowe is the difference between the numbers. If this number is less than the total payments and credits, "
				+ "then the value of your tax refund \nis the difference between the numbers." + "\n\nYou have now finished this section "
				+ "of the 1040EZ tax form. All that is left is a signature and to write your occupation next to it \nand it is done! " + ""
				+ "Congratulations, you have done your Form 1040EZ tax return.");
	}
	
	public static void Form1040A() {
		System.out.println("\"There are a few different steps to fill out the 1040A tax form.\n\n"
				+ "The first is to provide some personal information including your first and last name and middle initial, your social security number, your spouse's\n"
				+ "information, and your address." 
				+ "\n\nYou then have to give your filing status and find all eligible tax exemptions. This includes all of the dependents that you can claim. You can claim"
				+ "\nyourself if no one else does as well as your spouse and any children that you may have." 
				+ "\n\nYou then calculate your total income and your adjusted gross income before you move on to the tax, credits, and payments section. There are multiple" 
				+ "\ncalculations to be made in this section that will leave you with your total payments and credits and your total  These numbers determine if you \n"
				+ "get a tax refund or if you owe money."
				+ "\n\nTotal payments and credits is the amount of money that you have paid throughout the year in tax credits. This number is subtracted from the total\n"
				+ "tax because it is taxes that were already paid." 
				+ "\n\nThe total tax is the total amount of taxes that you owe. If this number is greater than the total payments and credits, then the amount you owe is the\n" 
				+ "difference between the numbers. If this number is less than the total payments and credits, then the value of your tax refund is the difference between "
				+ "\nthe numbers." 
				+ "\n\nYou have now finished this section of the 1040A tax form. All that is left is a signature and to write your occupation next to it and it’s done!"
				+ "\nCongratulations, you have done your Form 1040A tax return.");
	}
	
	public static void FormW4(){
		/*
		Single or Married filing separately
		Married filing jointly or Qualifying surviving spouse
		Head of household (if you’re unmarried and pay more than half the costs of keeping up a home for yourself and a qualifying individual)
		Define what the options mean so users know what they are

		“Step 4 (optional): Other Adjustments”

		“There are three parts to step 4. First, you will input any other income you get excluding that from your job. Then, we’ll walk you through the process to claim deductions. Finally, you can enter any extra tax withholdings.”
		Explain what it means to withhold tax

		“(A) Other income (not from jobs). If you want tax withheld for other income you expect this year that won’t have withholding, enter the amount of other income here. This may include interest, dividends, and retirement income: ”

		“(B) Deductions. If you expect to claim deductions other than the standard deduction and want to reduce your withholding, use the Deductions Worksheet on page 3 and enter the result here: ”
		Explain what deductions are
		Go through process of deductions with worksheet

		“(C) Extra withholding. Enter any additional tax you want withheld each pay period: ”
		*/
				
				Scanner scan = new Scanner(System.in);
				
				boolean looping = true; //if any looping is needed for any questions, we can use this boolean
				int Step3;
					int children = 0;
					int step3PartA;
					int step3PartB;
				//int Step4;
					int deduction = 0;
					int otherIncome = 0;
					int extraWithholding = 0;
					
				
				System.out.println("\n\nYou have chosen to get a job. You were idling away on your phone when you came across a notice: Best-est Buy-er was looking to hire! \nYou decide to work there to get some side moola, as you are in total debt. Now, you are required to fill out the Employee's Withholding Certificate, \nalso known as the W-4 tax form."
						+ "\r\n\n"
						+ "This tax form is used by employees, like yourself, to indicate how much federal income tax should be deducted from your paychecks (this is what Tax Withholding is). \nYou not only complete this form when you start a new job, but also when you experience significant life changes (e.g., getting married or having a child) \nor wish to adjust your tax withholding."
						+ "\r\n\n"
						+ "Now, let’s take you through the steps!\n\n"
						+ "Press enter to continue when ready.");
				scan.nextLine();
				
				//Step 1
				System.out.println("\r\n\n" + "-----------------------------------------------------------------------------------------------\n" +
						
						"Step 1: Entering Personal Information\n\n" +
						
						"In this step, you will enter some personal information, such as your first, middle, and last name, your social security number, address, and filing status. \r\n"
						+ "\r\n"
						+ "Let’s select a scenario to help you choose a filing status.\r\n"
						+ "");
				
				while (looping) {
					
					System.out.println("Choose an option:\n" +
							"Option 1: You are single and happy. Maybe.\n" +
							"Option 2: You are married but hate your spouse with a passion. However, you put up with it because you are a pure soul filled \nwith patience and tolerance. Also, your spouse is rich.\n" +
							"Option 3: You married a rich jerk. As time passed by, your spouse became more annoying and you had no choice but to kill your \nspouse. Don’t worry though, no one will ever know or care about it, as your spouse was hated by the world anyway :)\n" +
							"Option 4: You are married and living a happy life. You and your spouse overcome challenges together and trust each other.");
					String filingStatus = scan.nextLine();
					String redo;
					System.out.println(" ");
					switch (filingStatus){
					case "1":{
						System.out.println("You are single, and will be filing your taxes by yourself.\n");
						System.out.println("Would you like to re-do the question to learn more about other filing status'? (Enter 'y' for yes, 'n' for no)");
						redo = scan.nextLine();
						if((redo.toLowerCase()).equals("y"))looping = true;
						else looping = false;
						break;
					}
					case "2":{
						System.out.println("You have chosen the ‘married filing separately’ option. You are married, but choose to file your taxes separately from your spouse.\n");
						System.out.println("Would you like to re-do the question to learn more about other filing status'? (Enter 'y' for yes, 'n' for no)");
						redo = scan.nextLine();
						if((redo.toLowerCase()).equals("y"))looping = true;
						else looping = false;
						break;
					}
					case "3":{
						System.out.println("You have chosen the “Qualifying Surviving Spouse” option. This option allows you to retain the benefits of the Married Filing Jointly \nstatus for two years after your spouse’s death. This means that you and your spouse were filing \nyour taxes together before your spouse passed away. However, you must have a dependent child to file as a “Qualifying Surviving Spouse.”\n");
						System.out.println("Would you like to re-do the question to learn more about other filing status'? (Enter 'y' for yes, 'n' for no)");
						redo = scan.nextLine();
						if((redo.toLowerCase()).equals("y"))looping = true;
						else looping = false;
						break;
					}
					case "4":{
						System.out.println("You have chosen the “married filing jointly” option. This means both yours and your spouse’s income, deductions, and credits will be \nfiled jointly (together) rather than filing separate tax papers.\n");
						System.out.println("Would you like to re-do the question to learn more about other filing status'? (Enter 'y' for yes, 'n' for no)");
						redo = scan.nextLine();
						if((redo.toLowerCase()).equals("y"))looping = true;
						else looping = false;
						break;
					}
					default: 
						System.out.println("Please enter the option number of your choice.");
						break;
					}
				}
				
				System.out.println("In conclusion, you will enter your full name, social security number, and address. You will also check one of the three filing status' that applies to you on the W-4 form in step 1: \n1) Single or Married filing separately \n2) Married filing jointly or Qualifying surviving spouse \n3) Head of household\n"
				//should I add: Do you want more information about filing status'? and just print out what all of them mean?
						+ "\nYou have now completed step 1.\n");
				
				
				//Step 2
				System.out.println("Let's move on to step 2. Press enter to continue when ready.");
				scan.nextLine();
				System.out.println("\r\n\n" + "-----------------------------------------------------------------------------------------------");

				System.out.println("Steps 2-4 may be skipped if they do not apply to you. Let's go through them together and see if we need to fill them out!\n\n" +
								"Step 2: Multiple Jobs or Spouse Works\n");
				
				
				System.out.println("In this step, you only need to fill it out if you \n1) have more than one job or \n2) are married filing jointly and your spouse also works"
						+ "\n\nFor now, since you have only one job and your spouse (if any) does not work, here's some information to help you understand step 2."
						+ " What this step will do is calculate a withholding amount depending on the incomes earned from these multiple jobs."
						+ "\nYou will choose only ONE of three options to calculate your income withholding: \nUsing the 2023 tax form, part (a) is reserved for future use, meaning there is no option for part (a) as of now, but the government may add an option in the future."
						+ "\nIf you choose option (b), you will use the 'Multiple Jobs Worksheet' (typically found on page 3) and enter the result in Step 4(c).\n"
						+ "\n\tIf you chose this option, you must complete the 'Multiple Jobs Worksheet' for only ONE Form W-4 (you don't need to fill this worksheet out again on your other job's W-4 form)\n\t"
						+ "For better accuracy, fill out this worksheet for the highest paying job.\n\t"
						+ "The worksheet consists of multiple steps based off a table found on the next page (related to your [and your spouse's] incomes) and the number of pay periods per year.\n\t"
						+ "You will then do some calculations.\n\n\t"
						+ "For example, you fill out step 1 of the worksheet because you have two jobs, and using the table, you get a value of $3,740.\n\t"
						+ "Then, you skip to step 3, where you get paid 26 times per year.\n\t"
						+ "Finally, in step 4, you will divide the amount in step 1 by step 3: 3740 / 26 = $143.85\n"
						+ "\nUsing this example, you would enter $143.85 into step 4(c) of the Form W-4."
						+ "\n\nYou may also choose option (c) instead of (b), where all you will have to do is check option (c) on all your W-4 forms (for all your jobs).\n"
						+ "\n\tHowever, the difference between option (b) is that you are recommended to choose step (c) if you only have two jobs and your lower paying job is more than half of your higher paying job."
						+ "\n\tSo for example, you work at job A and job B. Job A pays you $100,000 and job B pays you $70,000. \n\tThis option would me more accurate than option (b) because job B (the lower paying job) pays you more than half of the pay of job A (the higher paying job)."
						+ "\n");
				
				System.out.println("In conclusion, there are some options you may choose from if you have multiple jobs or your spouse works.\n"
						+ "For steps 3-4, fill them out for only ONE of your jobs (if you have multiple jobs). For better accuracy, use your highest paying job.\n\nYou have now completed step 2. Press enter to continue when ready.");
				scan.nextLine();
				System.out.println("\r\n\n" + "-----------------------------------------------------------------------------------------------");
				
				
				//Step 3
				System.out.println("Step 3: Claim Dependent and Other Credits\n");

				System.out.println("In this step, you will calculate certain things related to your dependents if: " +
								"Your total income will be $200,000 or less ($400,000 or less if married filing jointly)\n"
								+ "Since your total income is less than $200,000 (you're just a college student working at Best-est Buy-er, sorry to break it to you), let's select some choices that may affect step 3.\n");

				looping = true;
				while (looping) {
					System.out.println("Choose an option number to determine how many kids you have (they are all under the age of 17):\n" +
							"Option 1: 0 children\n" +
							"Option 2: 1 child\n" +
							"Option 3: 2 children\n" +
							"Option 4: 10 children");
					String numOfChild = scan.nextLine();
					switch (numOfChild){
					case "1":{
						children = 0;
						looping = false;
						break;
					}
					case "2":{
						children = 1;
						looping = false;
						break;
					}
					case "3":{
						children = 2;
						looping = false;
						break;
					}
					case "4":{
						children = 10;
						looping = false;
						break;
					}
					default: 
						System.out.println("Please enter the option number of your choice.");
						break;
					}
				}
				System.out.println("\nNow that you have your number of children, you will multiply it by a certain number to determine your child tax credit. This amount will help calculate your total tax withholding."
						+ "\nUsing the 2023 year W-4 tax form, let's multiply your number of children by 2000. However, this value may change in other years.\n");
				
				step3PartA = children*2000;
				System.out.println("$2,000 multiplied by " + children + " (your number of children) leaves you with $" + step3PartA +".\n"
						+ "\nLet's select another choice for step 3.\n");
				
				int dep = 0;
				looping = true;
				while (looping) {
					System.out.println("Choose an option:\n" +
							"Option 1: You were raised in a family that firmly believes in filial piety. Therefore, you take care of both your spouse’s parents financially.\n" +
							"Option 2: College life is hard. You’re lonely and depressed. That’s why when you find a little girl playing next to a water fountain, you offer her ice cream and take her home. You now support her financially as if she was your daughter.\n" +
							"Option 3: Your parent’s refuse to pay for your college education. They want you to mature into a responsible adult. Therefore, they also throw your older triplet siblings on you, and you must now support them financially.\n" +
							"Option 4: You do not support anyone. You are poor, tired, and stressed enough as it is, and the thought of having to socialize with anyone makes you want to murder someone.");
					String dependent = scan.nextLine();
					switch (dependent){
					case "1":{
						dep = 2;
						looping = false;
						break;
					}
					case "2":{
						dep = 1;
						looping = false;
						break;
					}
					case "3":{
						dep = 3;
						looping = false;
						break;
					}
					case "4":{
						dep = 0;
						looping = false;
						break;
					}
					default: 
						System.out.println("Please enter the option number of your choice.");
						break;
					}
				}
						
				System.out.println("You have " + dep + " dependent(s).\n\n"
						+ "This question determined the number of other dependents (that are not your children under the age of 17) that you have."
						+ "\nSimilar to the previous part, you will multiply your number of dependents by a certain number to determine your credit. This amount will also help calculate your total tax withholding."
						+ "\nUsing the 2023 year W-4 tax form, let's multiply your number of dependents by 500. However, this value may change in other years.\n");
				step3PartB = dep*500;
				Step3 = step3PartA + step3PartB;
				System.out.println("$500 multiplied by " + dep + " (your number of other dependents) leaves you with $" + step3PartB +
						"\n\nAfter you have your dependent credits, you may also add any other credits in step 3, such as educational credits, foreign tax credit, adoption credit, etc."
						+ "\nLet's assume you have no other credits. Therefore, your total amount of credits as calculated in step 3 is $" + step3PartA + " (qualifying children credit) plus $" + step3PartB + " (other dependent credit) plus $0 (other credits)\n\n$" + step3PartA + " + $" + step3PartB + " + $0 = $" + Step3 + "\n");
				
				System.out.println("In conclusion, step 3 is all about calculating your total credits from dependents and others. Keep the amount calculated from step 3 in mind for later!\nYou have now completed step 3.\n");
				System.out.println("Press enter to continue when ready.");
				scan.nextLine();
				System.out.println("\r\n\n" + "-----------------------------------------------------------------------------------------------");

			
				//Step 4
				System.out.println("Step 4 (Optional): Other Adjustments");
				
				System.out.println("\nIn this step, you will add any other incomes, deductions, and extra withholding to calculate other adjustments."
						+ "\nThis step accounts for adjustments that are not addressed in the standard withholding calculation, and will help calculate an accurate total withholding.\n\n"
						+ "There are three parts in step 4: \n\n(a) Other income (not from jobs) \n\tIn this part, you will enter any other incomes that aren't from your job. This can be anything such as interest, dividends, and retirement income."
						+ "\n(b) Deductions \n\tThere are various deductions that you may enter in this step. Deductions are certain expenses that can be subtracted from your total income when filing taxes.\n\t"
						+ "Essentially, if you have deductions, your taxable income will decrease and you will have to pay less taxes.\n\t"
						+ "There are different types of deductions. Some include Standard Deductions (a fixed number determined through your filing status), Itemized Deductions (Certain "
						+ "\n\texpenses (like medical or charitable expenses) that you may itemize if they exceed the standard deduction), Above-the-Line Deductions (deductions for "
						+ "\n\tcontributions to retirement accounts, student loan interest, and educator expenses), and Business Deductions (business-related expenses like employee salaries)."
						+ "\n\tThe deductions section in the W-4 form is available if you want to claim a deduction OTHER THAN the Standard Deduction. If so, you will use "
						+ "\n\tthe Deductions Worksheet (typically found on page 3) to enter the calculated amount."
						+ "\n(c) Extra withholding"
						+ "\n\tYou will enter any additional tax you want withheld each pay period in part c, beyond the standard withholding calculated based on your allowances."
						+ "\n\tThis number may have already been calculated in Step 2, so it could already be filled out. However, if you have only one job or if you're not using the "
						+ "\n\t\"Multiple Jobs Worksheet,\" you can still enter any additional amount you want to be withheld voluntarily."
						+ "\n\nLet's answer a question to make it easier to understand how Step 4 works.\n");
			
				System.out.println("Press enter to continue when ready.");
				scan.nextLine();
				
				looping = true;
				while (looping) {
					System.out.println("Choose an option number:\n" +
							"Option 1: Your desperation for money reaches another level, and you turn to paid online surveys as a side gig.\n" +
							"Option 2: You felt depressed and decided to press your luck by going to a casino to gamble for the last time.\n" +
							"Option 3: Other incomes? Other deductions? Who needs those, you're paying more than enough as it is in your college!\n" +
							"Option 4: You decided the financial burden was too much for you. You adopt a child to bear the burden for you when they grow older and start working.");
					String deductions = scan.nextLine();
					System.out.println(" ");
					switch (deductions){
					case "1":{
						System.out.println("You have an 'other income' of $500.");
						otherIncome = 500;
						looping = false;
						break;
					}
					case "2":{
						System.out.println("Luck pities you, and you win $1,000. You now have an 'other income' of $1,000.");
						otherIncome = 1000;
						looping = false;
						break;
					}
					case "3":{
						System.out.println("You are qualified for an education deduction. You will fill out the Deductions Worksheet in the W-4 form to calculate how much this deduction will be \n"
								+ "along with any other values. Since you have no other deductions, you get a deduction of $2,500.");
						deduction = 2500;
						looping = false;
						break;
					}
					case "4":{
						System.out.println("You are qualified for a child-related deduction and get adoption credits of $10,000 (You use this money for the child only). You will fill out the \n"
								+ "Deductions Worksheet in the W-4 form to calculate how much this deduction will be along with any other values. Since you have no other deductions,"
								+ "\nyou get a deduction of $2,500.");
						deduction = 10000;
						looping = false;
						break;
					}
					default: 
						System.out.println("Please enter the option number of your choice.");
						break;
					}
				}
				
				System.out.println("\nNow, you'll fill out the W-4 form. \n\t4(a), you have a total of $" + otherIncome + " from 'Other Income.'\n\t"
								+ "4(b), you have a total of $" + deduction + " from 'Deductions.'\n\t"
								+ "4(c), you have a total of $" + extraWithholding + " from 'Extra Withholdings.'\n"
								+ "\nAfter filling out the blanks, you won't have to do any further calculations for this step. These amounts will help form an accurate tax withholding."
								+ "\n\nIn conclusion, step 4 is all about calculating further withholding from other adjustments, namely other income, deductions, and extra withholding.");
				//Side note: couldn't do the more info or dictionary/glossary option
				System.out.println("You have completed Step 4.\n");
				System.out.println("Press enter to continue when ready.");
				scan.nextLine();
				System.out.println("\r\n\n" + "-----------------------------------------------------------------------------------------------");
				
				
				//Step 5
				System.out.println("Step 5: Sign Here\n");
				
				System.out.println("You're almost done with your W-4 tax form. The last step includes reading a statement, such as: \n\n"
						+ "\"Under penalties of perjury, I declare that this certificate, to the best of my knowledge and belief, is true, correct, and complete.\"\n\n"
						+ "and writing your signature along with the date.");
				
				System.out.println("You have completed Step 5.\n");
				System.out.println("Press enter to continue when ready.");
				scan.nextLine();
				System.out.println("\r\n\n" + "-----------------------------------------------------------------------------------------------");
				
				
				//Additional Information
				System.out.println("Additional Information");
				
				System.out.println("The final part of the W-4 form includes and Employer's Only section. Worry not, as your employer will fill this part out for you."
						+ "\n\n-----------------------------------------------------------------------------------------------"
						+ "\n\nCongratulations, you have completed your W-4 Tax Statement!"
						+ "\n\n***********************************************************************************************");
	}
}
