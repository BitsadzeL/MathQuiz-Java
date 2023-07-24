import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.ArrayList;

public class Quiz {
    public static void main(String[] args) throws FileNotFoundException {
        //Automaticlly create file to store results
        try {
            File result = new File("results.txt");
            result.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }        
    
        Random random=new Random();

        File questionFile = new File("questions.txt");
        Scanner read = new Scanner(questionFile); //read from questions' file
        Scanner userInput = new Scanner(System.in); //for user answers

        ArrayList<String>  allQuestions=new ArrayList<String>(); //store all questions from file into this arraylist to choose random ones
        ArrayList<String> randomQuestions=new ArrayList<String>(); //store random ones in this collection        

        int quantity=Integer.parseInt(read.nextLine());
        int userAnswer;
        int points=0;
        String name;

        //I am keeping all of the questions in "allQuestions" collection. This makes easy for me to choose random questions.
        for(int i=0;i<quantity;i++){
            allQuestions.add(read.nextLine());
        }
        read.close();        



        System.out.print("Enter your name and click enter: ");
        name=userInput.nextLine();
        System.out.print("Enter number of questions and click enter to start: ");
        int n=userInput.nextInt(); //number of questions user wants to answer
        if(n<=0 || n>1000){
            System.out.println("Enter correct number of questions, please!");
        }else{

            for(int i=0;i<n;i++){
                int index = random.nextInt(allQuestions.size());
                randomQuestions.add(allQuestions.get(index));
            }

            //We are keeping questions as a line,so we need to split it to print equation and understand the answer.
            //The answer is always the last element of the array.
            for(int i=0;i<n;i++){
                String[] line=randomQuestions.get(i).split(";");
                System.out.println((i+1)+")"+line[0]); //equation (first element of the array)
                int answer=Integer.parseInt(line[5]); //answer of the given equation
                System.out.print("Answer:");
                userAnswer=userInput.nextInt();
                if(userAnswer==answer){points++;}
            } 
        
            userInput.close();
            System.out.println("Result:"+points+"/"+n);  
            System.out.println("Your results are saved. You can see them in \"results.txt\" file");


        // save result in the "results.txt" file
        try {
            File result = new File("results.txt");
            FileWriter writeResult=new FileWriter(result, true);
            writeResult.append("name:"+name+";"+"result:"+points+"/"+n+'\n');    
            writeResult.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
     }  
    }
}
