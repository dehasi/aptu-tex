import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * User: Dmitriy Bandurin
 * Date: 12.12.11
 */
public class BalancedTree {
    public static void main(String[] args) throws IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(in);
        Pattern spliter = Pattern.compile(" ");
        final String nodes = bufferedReader.readLine();
        String[] list = spliter.split(nodes);
        Node tree = new Node(null, Integer.parseInt(list[0]));
        for (int i = 1; i < list.length; i++) {
            final int value = Integer.parseInt(list[i]);
            if(value != 0){
                tree.add(value);
            } else {
                break;
            }
        }
        System.out.println(tree.height);
    }
}

class Node {
    public Node parent;
    public Node left;
    public Node right;
    public int height;
    public int value;

    Node(Node parent, int value) {
        this.parent = parent;
        this.value = value;
        if (value == 0){
            this.height = 0;
        } else {
            this.height = 1;
        }
    }

    public void add(int value){
        if(this.value == value){
            return;
        }

        if(this.value < value){
            if(this.right != null){
                this.right.add(value);
            } else {
                this.right = new Node(this, value);
                this.update();
            }
        } else {
            if(this.left != null){
                this.left.add(value);
            } else {
                this.left = new Node(this, value);
                this.update();
            }
        }
    }

    private void update() {
        int height = this.height;
        if(this.left != null && this.height < this.left.height + 1){
            this.height = this.left.height + 1;
        }

        if(this.right != null && this.height < this.right.height + 1){
            this.height = this.right.height + 1;
        }

        if(height != this.height && this.parent != null){
            this.parent.update();
        }
    }
}