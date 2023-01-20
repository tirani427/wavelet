import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String[] words = new String[];
    int num = 0;

   public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Words: %d", words);
            return String.format("User's Number: %d", words);
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        } else if(url.getPath().equals("/add")){
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
              num++;
              words[num] = parameters[1];
              return String.format("%s has been added to words. It is now %w",parameters[1],words);
            }
        }
        return "404 Not Found!";
        
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
      
        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

