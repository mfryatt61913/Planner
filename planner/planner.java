import java.util.*;
import java.io.*;
import java.text.*;
public class planner {
    public static void readPlanner(ArrayList<Homework> planner) {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM", Locale.ENGLISH);
        refreshPlanner(planner);
        if (planner.size() != 0) {
            System.out.println("\tSubject\t\tDue in\t\tDescription");
            for (int i=0; i<planner.size(); i++) {
                if (planner.get(i).getSubject().length() > 7) { //so that it stays in line
                    System.out.println(i + "\t" + planner.get(i).getSubject() + "\t" + formatter.format(planner.get(i).getDueDate()) + "\t" + planner.get(i).getDescription());
                } else {
                    System.out.println(i + "\t" + planner.get(i).getSubject() + "\t\t" + formatter.format(planner.get(i).getDueDate()) + "\t" + planner.get(i).getDescription());
                }
            }
        } else {
            System.out.println("Your planner is currently empty");
        }
    }
    public static void addHomeworkToPlanner(ArrayList<Homework> planner) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String subject = "";
        String due = "";
        Date due_date;
        String description = "";
        String more_homework = "y";
        SimpleDateFormat format = new SimpleDateFormat("d MMMM", Locale.ENGLISH);
        do {
            System.out.println("Please enter the subject");
            subject = sc.nextLine();
            System.out.println("Please enter the description");
            description = sc.nextLine();
            System.out.println("When it is due in? (in the format '1 January', etc)");
            due = sc.nextLine();
            due_date = format.parse(due);
            planner.add(new Homework(subject, due_date, description));
            System.out.println("Would you like to add another homework item? y/n");
            more_homework = sc.nextLine();
        } while (more_homework.equals("y"));
    }
    public static void amendHomeworkInPlanner(ArrayList<Homework> planner) throws ParseException {
        Scanner sc = new Scanner(System.in);
        int item = 0;
        String choice = "";
        String description = "";
        String date = "";
        SimpleDateFormat format = new SimpleDateFormat("d MMMM", Locale.ENGLISH);
        readPlanner(planner);
        System.out.println("Which homework item would you like to update? (enter the number)");
        item = sc.nextInt();
        sc.nextLine();
        System.out.println("Would you like to:");
        System.out.println("A\tUpdate the description");
        System.out.println("B\tUpdate the date it is due in");
        System.out.println("C\tMark as complete");
        choice = sc.nextLine();
        if (choice.equals("A")) {
            System.out.println("Please enter the new description");
            description = sc.nextLine();
            planner.get(item).setDescription(description);
        } else if (choice.equals("B")) {
            System.out.println("Please enter the new date (in the format '1 January', etc)");
            date = sc.nextLine();
            planner.get(item).setDueDate(format.parse(date));
        } else if (choice.equals("C")) {
            planner.get(item).setComplete();
            System.out.println("Done...");
        }
    }
    public static void refreshPlanner(ArrayList<Homework> planner) {
        for (int i=0; i<planner.size(); i++) {
            if (planner.get(i).getComplete()) {
                planner.remove(i);
                i--;
            }
        }
    }
    public static void savePlanner(ArrayList<Homework> planner) throws IOException {
       FileWriter fw = new FileWriter("C:\\Users\\Molly\\Documents\\School Work\\CompSci\\planner.txt");
       BufferedWriter bw = new BufferedWriter(fw);
       SimpleDateFormat formatter = new SimpleDateFormat("d MMMM", Locale.ENGLISH);
       refreshPlanner(planner);
       for (int i=0; i<planner.size(); i++) {
           bw.write(planner.get(i).getSubject() + "\t" + formatter.format(planner.get(i).getDueDate()) + "\t" + planner.get(i).getDescription());
           bw.newLine();
       }
       System.out.println("Done.");
       bw.close();
    }
    public static void main(String[] args) throws IOException, ParseException{
        Scanner sc = new Scanner(System.in);
        ArrayList<Homework> hw_to_do = new ArrayList<>();
        String menu_selection = "";
        SimpleDateFormat format = new SimpleDateFormat("d MMMM", Locale.ENGLISH);
        //read in planner from text file
        String[] line_split_up = new String[4];
        FileReader fr = new FileReader("C:\\Users\\Molly\\Documents\\School Work\\CompSci\\planner.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while ((line = br.readLine()) != null) {
            line_split_up = line.split("\t");
            hw_to_do.add(new Homework(line_split_up[0], format.parse(line_split_up[1]), line_split_up[2]));   
        }
        br.close();
        
        do {
            System.out.println("Would you like to:");
            System.out.println("A\tRead your planner");
            System.out.println("B\tAdd homework to your planner");
            System.out.println("C\tAmend homework in your planner");
            System.out.println("D\tClose your planner");
            menu_selection = sc.nextLine();
            switch (menu_selection){
                case "A":
                readPlanner(hw_to_do);
                break;
                case "B":
                addHomeworkToPlanner(hw_to_do);
                break;
                case "C":
                amendHomeworkInPlanner(hw_to_do);
                break;
                case "D":
                savePlanner(hw_to_do);
                break;
            }
        } while(!(menu_selection.equals("D"))); 
    }
}