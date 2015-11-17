/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author George Charalampidis
 */
public class PasswordChecker {
    
    private PasswordChecker() {
        
    }
    
    public static String passwordStrength(String str)
    {
		int size = str.length();
		int passwordStrength = 0;
		String passwordResult;
		boolean hasSixOrMore,hasDigit,hasLowerCase,hasUpperCase,hasSymbol;
		hasSixOrMore = hasDigit = hasLowerCase = hasUpperCase = hasSymbol = false;
		char ch;
                
		int i = 0;
	    while(i < size)
	    {
			ch = str.charAt(i);
			
			if(Character.isDigit(ch) && hasDigit == false)
			{
				hasDigit = true;
				passwordStrength++;
			}
			else if(Character.isLowerCase(ch) && hasLowerCase == false)
			{
				hasLowerCase = true;
				passwordStrength++;
			}
			else if(Character.isUpperCase(ch) && hasUpperCase == false)
			{
				hasUpperCase = true;
				passwordStrength++;
			}
			else
			{
				if(!Character.isLetter(ch) && hasSymbol == false)
				{
					hasSymbol = true;
					passwordStrength++;
				}
					
			}
			i++;
	    }
			
    	if(size >= 8)
            {
                hasSixOrMore = true;
                passwordStrength++;
            }
    	
        switch (passwordStrength)
        {
            case 1:
                    passwordResult = "Password Strength: 1 (Very Weak)";
                    break;
            case 2:
                    passwordResult = "Password Strength: 2 (Weak)";
                    break;
            case 3:
                    passwordResult = "Password Strength: 3 (Sufficient)";
                    break;
            case 4:
                    passwordResult = "Password Strength: 4 (Strong)" ;
                    break;
            case 5:
                    passwordResult = "Password Strength: 5 (Exceptional)";
                    break;
            default: 
                    passwordResult = "Error in password strength calculation!";
                    break;
        }
    	
    	return passwordResult; 
    }
    
}
