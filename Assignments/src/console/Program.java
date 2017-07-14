package console;

import questions.*;

public class Program {
    private static IRunner[] questionsArr = {new A1(), new A2(), new A3(), new A4(), new A5(), new A6(), new A7(), new A8(),
                                             new N1(), new N2(), new N3(), new N4(), new N5(), new N6(), new N7(), new N8()};

    public static void main(String args[]) {
        for (IRunner question : questionsArr){ 
        	System.out.println("----\n" + question.getQuestionID() + "\n----");
        	question.Run(getRelativeUrl("../inputs/" + question.getQuestionID()));
        }
    }

    private static String getRelativeUrl(String file) {
         java.net.URL url = Program.class.getResource(file);
         return url != null ? url.getFile() : null;
    }
}