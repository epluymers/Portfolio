package OracleDBConn;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
 
public class ProgramDriver
{
	//Public variables holding main user information during the program
	public static int memberId;
	public static int userType = 2;
	
	//Main program that controls user input and calls functions for processing
	public static void main(String[] args) throws SQLException 
	{
		//Login
		Scanner scanner=new Scanner(System.in);
		String username;
		String password;
		System.out.println("Welcome to the movie rental system.");
		
		boolean exit = false;
		int selection;
		while(!exit)
		{
			switch(userType)
			{
			case 0:
				System.out.print("\n Admin Screen \n"
						+ "1) Movie Catalog Manager \n"
						+ "2) Member Catalog Manager \n"
						+ "3) Switch to Member View \n"
						+ "4) Logout & Exit \n"
						+ "5) Logout \n"
						+ "Please Make a selection: ");
				selection = scanner.nextInt();
				scanner.nextLine();
				switch(selection)
				{
					case 1:
						while(selection != 5)
						{
							System.out.print("\n Movie Catalog Manager \n"
									+ "1) Search Movie \n"
									+ "2) Add Movie \n"
									+ "3) Update Movie \n"
									+ "4) Delete Movie \n"
									+ "5) Return to previous screen \n"
									+ "Please Make a selection: ");
							selection = scanner.nextInt();
							scanner.nextLine();
							switch(selection)
							{
								case 1:
									while(selection != 4)
									{
										System.out.print("\n Search for Movie \n"
											+ "1) Search by ID \n"
											+ "2) Search by Title \n"
											+ "3) Browse by Category \n"
											+ "4) Return to previous screen \n"
											+ "Please make a selection: ");
										selection = scanner.nextInt();
										scanner.nextLine();
										switch(selection)
										{
											case 1:
												System.out.print("\n Search by ID \n"
														         + "Please enter the ID number of the movie you are looking for: ");
												int idinput = scanner.nextInt();
												scanner.nextLine();
												System.out.println("Here are the movie(s) found based on your search");
												IDSearch(idinput);
												break;
											case 2:
												System.out.print("\n Search by Title \n"
												         + "Please enter the Title or Keyword of the movie you are looking for: ");
												String tinput = scanner.nextLine();
												System.out.println(tinput);
												TitleSearch(tinput);
												break;
											case 3:
												System.out.print("\n Search by Category \n"
														+ "1) SciFi \n"
														+ "2) Horror \n"
														+ "3) Western \n"
														+ "4) Comedy \n"
														+ "5) Drama \n"
														+ "Please select a category: ");
												int catinput = scanner.nextInt();
												scanner.nextLine();
												System.out.println(catinput);
												CatSearch(catinput);
												break;
											case 4:
												break;
											default:
												System.out.println("Sorry that was an invalid input.");
												break;
										}
									}
									break;
								case 2:
									System.out.println("\n Add a Movie");
									System.out.println("Please enter all of the information when prompted");
									boolean validinput = false;
									while(!validinput)
									{
										System.out.print("Enter Movie Title: ");
										String tinput = scanner.nextLine();
										int cinput = 0;
										int vinput = 0;
										int qinput = 0;
										if(!tinput.equals("XX") && !tinput.equals("xx"))
										{
											System.out.print("1 = SciFi 2 = Horror 3 = Western 4 = Comedy 5 = Drama \n Enter Movie Category: ");
											cinput = scanner.nextInt();
											scanner.nextLine();
											System.out.print("Enter Movie Value: ");
											vinput = scanner.nextInt();
											scanner.nextLine();
											System.out.print("Enter Movie Quantity: ");
											qinput = scanner.nextInt();
											scanner.nextLine();
											if(cinput > 6 || cinput < 1 || qinput < 0 || vinput < 5)
											{
												System.out.println("\n Error one of your inputs is not valid. \n"
														+ "Please enter a valid Category Number and a non-negative Value and Quantity. \n"
														+ "Note: if you no longer wish to add a movie type XX for Movie Title");
											}
											else
											{
												System.out.print("\n Movie Title: " + tinput + "\n"
														+ "Category ID: " + cinput + "\n"
														+ "Price: $" + vinput + "\n"
														+ "Quantity: " + qinput + "\n"
														+ "Does this information look correct? ");
												char checkvalid = scanner.next().charAt(0);
												if(checkvalid == 'Y' || checkvalid == 'y')
												{
													addMovie(tinput, cinput, vinput, qinput);
													validinput = true;
												}
												else
												{
													System.out.println("\n Please reenter your information. \n"
															+ "Note: if you no longer wish to add a movie type XX for Movie Title");
												}
												
											}
										}
										else
										{
											validinput = true;
										}
									}
									break;
								case 3:
									System.out.println("\n Update a Movie");
									System.out.print("Please enter the Movie ID of the movie you would like to update: ");
									int idupdate = scanner.nextInt();
									scanner.nextLine();
									System.out.println("The Movie ID returns this movie");
									IDSearch(idupdate);
									boolean exitokay = false;
									while(!exitokay)
									{
										System.out.print("\n What field would you like to update? \n"
												+ "1) Title \n"
												+ "2) Category \n"
												+ "3) Price \n"
												+ "4) Quantity \n"
												+ "5) Cancel Update \n"
												+ "Please make a selection: ");
										int updatechoice = scanner.nextInt();
										scanner.nextLine();
										switch(updatechoice)
										{
										  	case 1:
										  		System.out.print("\n Please enter new title: ");
										  		String ntinput = scanner.nextLine();
										  		movieUpdate("MOVIE_TITLE", ntinput, 0, idupdate);
										  		exitokay = true;
										  		break;
										  	case 2:
										  		System.out.print("\n Please enter new category: ");
										  		int ncinput = scanner.nextInt();
										  		scanner.nextLine();
										  		if(ncinput > 0 && ncinput <= 5)
										  		{
											  		movieUpdate("MOVIE_CAT_ID", "", ncinput, idupdate);
											  		exitokay = true;
										  		}
										  		else
										  		{
										  			System.out.println("Error input not valid.");
										  		}
										  		break;
										  	case 3:
										  		System.out.print("\n Please enter new price: ");
										  		int npinput = scanner.nextInt();
										  		scanner.nextLine();
										  		if(npinput >= 5)
										  		{
										  			movieUpdate("MOVIE_VALUE", "", npinput, idupdate);
											  		exitokay = true;
										  		}
										  		else
										  		{
										  			System.out.println("Error input not valid.");
										  		}
										  		break;
										  	case 4:
										  		System.out.print("\n Please enter new quantity: ");
										  		int nqinput = scanner.nextInt();
										  		scanner.nextLine();
										  		if(nqinput > 0)
										  		{
										  			movieUpdate("MOVIE_QTY", "", nqinput, idupdate);
											  		exitokay = true;
										  		}
										  		else
										  		{
										  			System.out.println("Error input not valid.");
										  		}
										  		break;
										  	case 5:
										  		exitokay = true;
										  		break;
										  	default:
										  		System.out.println("Error selection not recognized.");
										  		break;
										}
									}
									break;
								case 4:
									System.out.println("\n Delete a Movie");
									System.out.print("Please enter the Movie ID of the movie you would like to delete: ");
									int idremove = scanner.nextInt();
									scanner.nextLine();
									System.out.println("The Movie ID returns this movie");
									IDSearch(idremove);
									System.out.print("\n Are you sure you wish to delete this movie? ");
									char doublecheck = scanner.next().charAt(0);
									if(doublecheck == 'Y' || doublecheck == 'y')
									{
										deleteMovie(idremove);
										System.out.println("\n Movie deleted. Returning to previous menu.");
									}
									else
									{
										System.out.println("\n Movie not deleted. Returning to previous menu.");
									}
									break;
								case 5:
									break;
								default:
									System.out.println("Sorry that was an invalid input.");
									break;
							}
						}
						break;
					case 2:
						while(selection != 6)
						{
							System.out.print("\n Member Catalog Manager \n"
									+ "1) Search Member \n"
									+ "2) Add Member \n"
									+ "3) Update Member \n" //Option to update payment info
									+ "4) Delete Member \n"
									+ "5) Member Delinquencies Report \n"
									+ "6) Return to previous screen \n"
									+ "Please Make a selection: ");
							selection = scanner.nextInt();
							scanner.nextLine();
							switch(selection)
							{
								case 1:
									while(selection != 4)
									{
										System.out.print("\n Search for a Member \n"
											+ "1) Search by ID \n"
											+ "2) Search by First Name \n"
											+ "3) Search by Last Name \n"
											+ "4) Return to previous screen \n"
											+ "Please make a selection: ");
										selection = scanner.nextInt();
										scanner.nextLine();
										switch(selection)
										{
											case 1:
												System.out.print("\n Search by ID \n"
														         + "Please enter the ID number of the member you are looking for.");
												int idinput = scanner.nextInt();
												scanner.nextLine();
												System.out.println(idinput);
												System.out.println("Here is the member found based on your search");
												MIDSearch(idinput);
												break;
											case 2:
												System.out.println("\n Search by First Name \n"
												         + "Please enter the First Name of the member you are looking for.");
												String finput = scanner.nextLine();
												MNameSearch("FIRST", finput); 
												break;
											case 3:
												System.out.println("\n Search by Last Name \n"
												         + "Please enter the Last Name of the member you are looking for.");
												String linput = scanner.nextLine();
												MNameSearch("LAST", linput); 
												break;
											case 4:
												break;
											default:
												System.out.println("Sorry that was an invalid input.");
												break;
										}
									}
									break;
								case 2:
									System.out.println("\n Add a Member");
									System.out.println("Please enter all of the information when prompted");
									boolean validinput = false;
									while(!validinput)
									{
										System.out.print("Enter Member First Name: ");
										String finput = scanner.nextLine();
										String linput = "";
										String liinput = "";
										String sinput = "";
										String cinput = "";
										char minput = ' ';
										if(!finput.equals("XX") && !finput.equals("xx"))
										{
											System.out.print("Enter Last Name: ");
											linput = scanner.nextLine();
											System.out.print("Enter License Number (9 Digits Max): ");
											liinput = scanner.nextLine();
											System.out.print("Enter 2 Letter State Abbreviation: ");
											sinput = scanner.nextLine();
											System.out.print("Enter Credit Card Number (12 Digits Max): ");
											cinput = scanner.nextLine();
											System.out.print("Add Member to mailing list?(y/n) ");
											minput = scanner.next().charAt(0);
											scanner.nextLine();
											System.out.println(sinput.length() + " " + minput);
											if(sinput.length() != 2 || liinput.length() > 9 || cinput.length() > 12 || (minput != 'Y' && minput != 'y' && minput != 'N' && minput != 'n'))
											{
												System.out.println("\n Error one of your inputs is not valid. \n"
														+ "Please make sure all information is entered properly. \n"
														+ "Note: if you no longer wish to add a member type XX for First Name");
											}
											else
											{
												System.out.println("\n First Name: " + finput + "\n"
														+ "Last Name: " + linput + "\n"
														+ "License Number: " + liinput + "\n"
														+ "State: " + sinput + "\n"
														+ "Credit Card Number: " + cinput + "\n"
														+ "Mailing List: " + minput + "\n"
														+ "Does this information look correct? ");
												char checkvalid = scanner.next().charAt(0);
												if(checkvalid == 'Y' || checkvalid == 'y')
												{
													addMember(finput, linput, liinput, sinput, cinput, minput);
													validinput = true;
												}
												else
												{
													System.out.println("Please reenter your information. \n"
															+ "Note: if you no longer wish to add a member type XX for First Name");
												}
												
											}
										}
										else
										{
											validinput = true;
										}
									}
									break;
								case 3:
									System.out.println("\n Update a Member");
									System.out.print("Please enter the Member ID of the member you would like to update: ");
									int idupdate = scanner.nextInt();
									scanner.nextLine();
									System.out.println("The Member ID returns this member");
									MIDSearch(idupdate);
									boolean exitokay = false;
									while(!exitokay)
									{
										System.out.print("\n What field would you like to update? \n"
												+ "1) First Name \n"
												+ "2) Last Name \n"
												+ "3) License Number \n"
												+ "4) State \n"
												+ "5) Credit Card \n"
												+ "6) Suspended \n"
												+ "7) Mailing List \n"
												+ "8) Cancel Update \n"
												+ "Please make a selection");
										int updatechoice = scanner.nextInt();
										scanner.nextLine();
										switch(updatechoice)
										{
										  	case 1:
										  		System.out.print("\n Please enter new First Name: ");
										  		String nfinput = scanner.nextLine();
										  		memberUpdate("FIRST", nfinput, idupdate);
										  		exitokay = true;
										  		break;
										  	case 2:
										  		System.out.print("\n Please enter new Last Name: ");
										  		String nlinput = scanner.nextLine();
										  		memberUpdate("LAST", nlinput, idupdate);
										  		exitokay = true;
										  		break;
										  	case 3:
										  		System.out.print("\n Please enter new License Number: ");
										  		String nliinput = scanner.nextLine();
										  		if(nliinput.length() <= 9)
										  		{
										  			memberUpdate("LICIENSE_NU", nliinput, idupdate);
											  		exitokay = true;
										  		}
										  		else
										  		{
										  			System.out.println("Error input not valid.");
										  		}
										  		break;
										  	case 4:
										  		System.out.print("\n Please enter new State: ");
										  		String nsinput = scanner.nextLine();
										  		if(nsinput.length() == 2)
										  		{
										  			memberUpdate("LICIENSE_ST", nsinput, idupdate);
											  		exitokay = true;
										  		}
										  		else
										  		{
										  			System.out.println("Error input not valid.");
										  		}
										  		break;
										  	case 5:
										  		System.out.print("\n Please enter new Credit Card: ");
										  		String ncinput = scanner.nextLine();
										  		if(ncinput.length() <= 12)
										  		{
										  			memberUpdate("CREDIT_CARD", ncinput, idupdate);
											  		exitokay = true;
										  		}
										  		else
										  		{
										  			System.out.println("Error input not valid.");
										  		}
										  		break;
										  	case 6:
										  		System.out.print("\n Please enter new Suspension state (y/n): ");
										  		char nsuinput = scanner.next().charAt(0);
										  		if(nsuinput == 'Y' || nsuinput == 'y' || nsuinput == 'N' || nsuinput == 'n')
										  		{
										  			memberUpdate("SUSPENSION", String.valueOf(nsuinput), idupdate);
											  		exitokay = true;
										  		}
										  		else
										  		{
										  			System.out.println("Error input not valid.");
										  		}
										  		break;
										  	case 7:
										  		System.out.print("\n Please enter new Mailing List choice (y/n): ");
										  		char nminput = scanner.next().charAt(0);
										  		if(nminput == 'Y' || nminput == 'y' || nminput == 'N' || nminput == 'n')
										  		{
										  			memberUpdate("MAILING_LIST", String.valueOf(nminput), idupdate);
											  		exitokay = true;
										  		}
										  		else
										  		{
										  			System.out.println("Error input not valid.");
										  		}
										  		break;
										  	case 8:
										  		exitokay = true;
										  		break;
										  	default:
										  		System.out.println("Error selection not recognized.");
										  		break;
										}
									}
									break;
								case 4:
									System.out.println("\n Delete a Member");
									System.out.print("Please enter the Member ID of the member you would like to delete: ");
									int idremove = scanner.nextInt();
									scanner.nextLine();
									System.out.println("The Member ID returns this member");
									MIDSearch(idremove);
									System.out.print("\n Are you sure you wish to delete this member? ");
									char doublecheck = scanner.next().charAt(0);
									if(doublecheck == 'Y' || doublecheck == 'y')
									{
										deleteMember(idremove);
										System.out.println("\n Member deleted. Returning to previous menu.");
									}
									else
									{
										System.out.println("\n Member not deleted. Returning to previous menu.");
									}
									break;
								case 5:
									getMemberNotifications();
									break;
								case 6:
									break;
								default:
									System.out.println("Sorry that was an invalid input.");
									break;
							}
						}
						break;
					case 3:
						System.out.print("\n Please enter Member ID for member account you wish to switch to: ");
						memberId = scanner.nextInt();
						scanner.nextLine();
						userType = 1;
						break;
					case 4:
						exit = true;
						break;
					case 5:
						userType = 2;
						break;
					default:
						System.out.println("Sorry that was an invalid input.");
						break;
				}
				break;
			case 1:
				if(checkSuspended(memberId))
				{
					System.out.println("\n   ATTENTION: You have overdue movies. You must return overdue movies before renting new movies.");
				}
				System.out.print("\n Member Screen \n"
						+ "1) Search Movies \n"
						+ "2) Rent Movie \n"
						+ "3) Return Movie \n"
						+ "4) View Rented Movies \n"
						+ "5) Logout & Exit \n"
						+ "6) Logout \n"
						+ "Please Make a selection: ");
				selection = scanner.nextInt();
				scanner.nextLine();
				switch(selection)
				{
					case 1:
						while(selection != 4)
						{
							System.out.print("\n Search for Movie \n"
								+ "1) Search by ID \n"
								+ "2) Search by Title \n"
								+ "3) Browse by Category \n"
								+ "4) Return to previous screen \n"
								+ "Please make a selection: ");
							selection = scanner.nextInt();
							scanner.nextLine();
							switch(selection)
							{
								case 1:
									System.out.print("\n Search by ID \n"
											         + "Please enter the ID number of the movie you are looking for.");
									int idinput = scanner.nextInt();
									scanner.nextLine();
									System.out.println(idinput);
									System.out.println("Here are the movie(s) found based on your search");
									IDSearch(idinput);
									break;
								case 2:
									System.out.println("\n Search by Title \n"
									         + "Please enter the Title or Keyword of the movie you are looking for.");
									String tinput = scanner.nextLine();
									System.out.println(tinput);
									TitleSearch(tinput);
									break;
								case 3:
									System.out.print("\n Search by Category \n"
											+ "1) SciFi \n"
											+ "2) Horror \n"
											+ "3) Western \n"
											+ "4) Comedy \n"
											+ "5) Drama \n"
											+ "Please select a category: ");
									int catinput = scanner.nextInt();
									scanner.nextLine();
									System.out.println(catinput);
									CatSearch(catinput);
									break;
								case 4:
									break;
								default:
									System.out.println("Sorry that was an invalid input.");
									break;
							}
						}
						break;
					case 2:
						if(checkSuspended(memberId))
						{
							System.out.println("Your account is currently suspended and you cannot rent movies at this time. Please speak with a team member.");
							scanner.nextLine();
						}
						else if(checkMoviesRented(memberId) >= 5)
						{
							System.out.println("You have already rented the maximum number of movies allowed. \n Please return a movie before renting another.");
							scanner.nextLine();
						}
						else
						{
							System.out.print("\n Rent a Movie \n"
									+ "Please enter a the ID for the movie you would like to rent. ");
							int movieID = scanner.nextInt();
							scanner.nextLine();
							System.out.println("\n You have chosen:");
							IDSearch(movieID);
							System.out.print("Proceed with rental and payment? (Y/N) ");
							char yesno = scanner.next().charAt(0);
							if(yesno == 'Y' || yesno == 'y')
							{
								if(checkRentable(movieID))
								{
									System.out.print("\n Rental Payment \n"
											+ "1) Charge to account \n"
											+ "2) Charge to card on file \n"
											+ "3) Pay with check at pickup \n"
											+ "4) Pay with cash at pickup \n"
											+ "5) Charge to Debit Card on file \n"
											+ "Please choose a payment method. ");
									int payinput = scanner.nextInt();
									scanner.nextLine();
									if(payinput >= 1 && payinput <= 5)
									{
										rentMovie(movieID, memberId, payinput);
										System.out.println("\n Movie Rental Successful. Enjoy Your Movie!");
									}
									else
									{
										System.out.println("Error that input was not valid. Please try again.");
									}
								}
								else
								{
									System.out.println("Sorry all copies of this movie are currently unavailable.");
								}
							}
						}
						break;
					case 3:
						System.out.println("\n Return a Movie. \n"
								+ "These are the movies you currently have checked out.");
						showMoviesRented(memberId);
						System.out.print("\n Enter the movie ID of the movie you would like to return: ");
						int idinput = scanner.nextInt();
						scanner.nextLine();
						boolean returned = returnMovie(idinput, memberId);
						if(returned)
						{
							System.out.println("\n Movie returned successfully.");
						}
						else
						{
							System.out.println("\n Error please confirm movie ID number and try again.");
						}
						break;
					case 4:
						System.out.println("\n View Rented Movies. \n"
								+ "These are the movies you currently have checked out.");
						showMoviesRented(memberId);
						break;
					case 5:
						exit = true;
						break;
					case 6:
						userType = 2;
						break;
					default:
						System.out.println("Sorry that was an invalid input.");
						break;
				}
				break;
			case 2:
				System.out.print("\n "
						+ "To exit program type exit. \n Please enter your username: ");
				username = scanner.nextLine();
				if(username.equals("exit")) 
				{
					exit = true;
				}
				else
				{
					System.out.print("Please enter your password: ");
					password = scanner.nextLine();
					Login(username, password);
				}
				break;
			default:
				System.out.println("\n Sorry that username and password is incorrect. Please Try again.");
				userType = 2;
				break;
				
			}
		}
		
		scanner.close();
	}
	
	//Takes in column name a String and int one of which is alway 0/NULL depending on column passed and movie id
	private static void movieUpdate(String col, String val1, int val2, int id)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_edit_movie(?,?,?,?)}");
			pstmt.setString(1, col);
			pstmt.setString(2, val1);
			pstmt.setInt(3, val2);
			pstmt.setInt(4, id);
			
			pstmt.execute();

			System.out.println("Movie Updated");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
	}
	
	//Takes in column name, String holding value to be updated and member ID
	private static void memberUpdate(String col, String val, int id)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_edit_member(?,?,?)}");
			pstmt.setString(1, col);
			pstmt.setString(2, val);
			pstmt.setInt(3, id);
			
			pstmt.execute();

			System.out.println("Member Updated");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
	}
	
	//Deletes member with the id passed
	private static void deleteMember(int iddelete)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_delete_member(?)}");
			pstmt.setInt(1, iddelete);
			
			pstmt.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
	}
	
	//Deletes movie with the id passed
	private static void deleteMovie(int iddelete)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_delete_movie(?)}");
			pstmt.setInt(1, iddelete);
			
			pstmt.execute();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
	}
	
	//Returns true if movie is returnable, IE exists as one of the member's rentals, Returns false if move cannot be returned for some reason, IE already returned or not in member's rentals
	private static boolean returnMovie(int movieid, int memberid)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_return_movie(?,?,?)}");
			pstmt.setInt(1, movieid);
			pstmt.setInt(2, memberid);
			pstmt.registerOutParameter(3, Types.INTEGER);
			
			pstmt.execute();
			
			if(pstmt.getInt(3) == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
		return false;
	}
	
	//Rents movie passed to member passed using payment method selected
	private static void rentMovie(int movieid, int memberid, int pay)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_rent_movie(?,?,?)}");
			pstmt.setInt(1, movieid);
			pstmt.setInt(2, memberid);
			pstmt.setInt(3, pay);
			
			pstmt.execute();
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
	}
	
	//Checks to see if there are available copies of the rental movie suggested
	private static boolean checkRentable(int idinput)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_movie_rentable(?,?)}");
			pstmt.setInt(1, idinput);
			pstmt.registerOutParameter(2, Types.INTEGER);
			
			pstmt.execute();
			
			if(pstmt.getInt(2) == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
		return false;
	}
	
	//Returns integer value for number of movies the member currently has out
	private static int checkMoviesRented(int idinput)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_movies_rented(?,?)}");
			pstmt.setInt(1, idinput);
			pstmt.registerOutParameter(2, Types.INTEGER);
			
			pstmt.execute();
			
			int rented = pstmt.getInt(2);
			return rented;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
		return 0;
	}
	
	//Returns true if member is suspended, IE is delinquent in returning rentals
	private static boolean checkSuspended(int idinput)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		PreparedStatement ps;

		try 
		{
			ps = dbConnection.getConnection().prepareStatement("select * from MM_MEMBER where MEMBER_ID = ?");
			ps.setInt(1, idinput);
			ResultSet rs = ps.executeQuery();
			boolean suspended = false;
			while (rs.next()) 
			{
				if(rs.getString("SUSPENSION").equals("Y") || rs.getString("SUSPENSION").equals("y"))
				{
					suspended = true;
				}
			}
			return suspended;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return true;
	}
	
	//Member ID Search
	private static void MIDSearch(int input)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		PreparedStatement ps;

		try 
		{
			ps = dbConnection.getConnection().prepareStatement("select * from MM_MEMBER where MEMBER_ID = ?");
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				System.out.println("\n Member ID: " + rs.getInt("member_id") + "\n First Name: " + rs.getString("FIRST") + "\n Last Name: " + rs.getString("LAST") + "\n Suspension: " + rs.getString("SUSPENSION") + "\n Mailing List: " + rs.getString("MAILING_LIST"));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Movie ID Search
	private static void IDSearch(int input)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		PreparedStatement ps;

		try 
		{
			ps = dbConnection.getConnection().prepareStatement("select * from MM_MOVIE where MOVIE_ID = ?");
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				System.out.println("\n Movie ID: " + rs.getInt("movie_id") + "\n Title: " + rs.getString("movie_title") + "\n Rental Price: " + rs.getInt("movie_value"));
				if(userType == 0)
				{
					System.out.println("Quantity: " + rs.getInt("movie_qty") + "\n");
				}
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Add a member
	private static void addMember(String first, String last, String license, String state, String card, char mail)
	{
		String smail = String.valueOf(mail);
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_add_member(?,?,?,?,?,?)}");
			pstmt.setString(1, first);
			pstmt.setString(2, last);
			pstmt.setString(3, license);
			pstmt.setString(4, state);
			pstmt.setString(5, card);
			pstmt.setString(6, smail);
			
			pstmt.execute();

			System.out.println("Member Added");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
	}
	
	//Add a movie
	private static void addMovie(String title, int cat, int val, int quan)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_add_movie(?,?,?,?)}");
			pstmt.setString(1, title);
			pstmt.setInt(2, cat);
			pstmt.setInt(3, val);
			pstmt.setInt(4, quan);
			
			pstmt.execute();

			System.out.println("Movie Added");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
	}
	
	//Display member's movies currently rented out
	private static void showMoviesRented(int idinput)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		PreparedStatement ps;

		try 
		{
			ps = dbConnection.getConnection().prepareStatement("Select MM_MOVIE.MOVIE_ID, MOVIE_TITLE, MOVIE_VALUE, TRUNC(SYSDATE - CHECKOUT_DATE) AS DAYSDIFF from MM_MOVIE, MM_RENTAL WHERE MEMBER_ID = ? AND CHECKIN_DATE IS NULL AND MM_MOVIE.MOVIE_ID = MM_RENTAL.MOVIE_ID");
			ps.setInt(1, idinput);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				System.out.println("\n Movie ID: " + rs.getInt("movie_id") + "\n Title: " + rs.getString("movie_title") + "\n Rental Price: " + rs.getInt("movie_value"));
				if(rs.getInt("daysdiff") > 5)
				{
					System.out.println("Status: Overdue");
				}
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Movie Category Search
	private static void CatSearch(int input)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		PreparedStatement ps;

		try 
		{
			ps = dbConnection.getConnection().prepareStatement("select * from MM_MOVIE where MOVIE_CAT_ID = ?");
			ps.setInt(1, input);
			ResultSet rs = ps.executeQuery();
			
			System.out.println("\n Here are the movie(s) found based on your search");
			
			while (rs.next()) 
			{
				System.out.println("\n Movie ID: " + rs.getInt("movie_id") + "\n Title: " + rs.getString("movie_title") + "\n Rental Price: " + rs.getInt("movie_value"));
				if(userType == 0)
				{
					System.out.println("Quantity: " + rs.getInt("movie_qty") + "\n");
				}
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Returns list of all members delinquent on renturning rentals
	private static void getMemberNotifications()
	{
		DBConnection dbConnection = DBConnection.getInstance();
		PreparedStatement ps;

		try 
		{
			ps = dbConnection.getConnection().prepareStatement("select * from MM_MEMBER where SUSPENSION = 'Y'");
			ResultSet rs = ps.executeQuery();
			
			System.out.println("\n The following members have overdue movies.");
			
			while (rs.next()) 
			{
				System.out.println("\n Member ID: " + rs.getInt("member_id") + "\n First Name: " + rs.getString("FIRST") + "\n Last Name: " + rs.getString("LAST") + "\n Suspension: " + rs.getString("SUSPENSION") + "\n Mailing List: " + rs.getString("MAILING_LIST") + "\n");
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Member search by Name, first String takes column name
	private static void MNameSearch(String col, String name)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		PreparedStatement ps;

		try 
		{
			if(col == "FIRST")
			{
				ps = dbConnection.getConnection().prepareStatement("select * from MM_MEMBER where LOWER(FIRST) LIKE '%' || LOWER(?) || '%'");
			}
			else
			{
				ps = dbConnection.getConnection().prepareStatement("select * from MM_MEMBER where LOWER(LAST) LIKE '%' || LOWER(?) || '%'");
			}
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) 
			{
				System.out.println("\n Member ID: " + rs.getInt("member_id") + "\n First Name: " + rs.getString("FIRST") + "\n Last Name: " + rs.getString("LAST") + "\n Suspension: " + rs.getString("SUSPENSION") + "\n Mailing List: " + rs.getString("MAILING_LIST"));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Member Title search
	private static void TitleSearch(String title)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		PreparedStatement ps;

		try 
		{
			ps = dbConnection.getConnection().prepareStatement("select * from MM_MOVIE where LOWER(MOVIE_TITLE) LIKE '%' || LOWER(?) || '%'");
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) 
			{
				System.out.println("\n Movie ID: " + rs.getInt("movie_id") + "\n Title: " + rs.getString("movie_title") + "\n Rental Price: " + rs.getInt("movie_value"));
				if(userType == 0)
				{
					System.out.println("Quantity: " + rs.getInt("movie_qty") + "\n");
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	//User login function, checks login table for valid username and password
	private static void Login(String un, String pw)
	{
		DBConnection dbConnection = DBConnection.getInstance();
		CallableStatement pstmt = null;
		try 
		{
			pstmt = dbConnection.getConnection().prepareCall("{call pkg_term_proj.p_login(?,?,?,?)}");
			pstmt.setString(1, un);
			pstmt.setString(2, pw);

			pstmt.registerOutParameter(3, Types.INTEGER);
			pstmt.registerOutParameter(4, Types.INTEGER);
			pstmt.execute();

			userType = pstmt.getInt(3);
			memberId = pstmt.getInt(4);
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

		}
	}
}