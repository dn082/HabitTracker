// this is my class Category and each category has a name
public class Category { 
    private String name; 

    public Category(String name) { 
        this.name = name;
    }

    public String getName() { 
        return name;
    }

    public void setName(String name) { 
        this.name = name;
    }

    @Override
    public String toString() {
        return name; 
    }
}