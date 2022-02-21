package oo.hide;


public class PointSet {

    public Integer capacity;
    private Point[] array;
    public Integer counter = 0;

    public PointSet(int capacity) {
        this.array = new Point[capacity];
        this.capacity = capacity;


    }
    public PointSet() {
        this(10);
    }

    public void add(Point point) {
        if(!contains(point)){
            if(counter == capacity){
                Point [] a = new Point[capacity * 2];
                array = a;
            }
            array[counter] = point;
            counter++;
        }
    }


    public int size() {
        return counter;
    }

    public String toString() {
        String text = "";
        for (Point i: array){
            if(i != null){
                text += String.format("(%s, %s), ", i.x, i.y);
            }
        }
        text = text.substring(0,text.length() - 2);
        return text;
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PointSet other)){
            return false;
        }
        for(Point i: other.array){
            if (!contains(i) && i!= null || other.size() != this.size()){
                return false;
            }
        }
        return true;
    }

    public boolean contains(Point point) {
        for (Point i : array) {
            if (i != null && i.equals(point)){
                return true;
                }
            }
        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet set2 = new PointSet();
        for(Point i: other.array){
            for(Point a: this.array){
                if(!a.equals(i)){
                    set2.add(a);
                    return set2;
                }
            }

        }
        return set2;
    }

    public PointSet intersect(PointSet other) {
        PointSet set = new PointSet();
        for(Point i: other.array){
            for(Point a: this.array){
                if(a.equals(i)){
                    set.add(i);
                    return set;
                }
            }

        }
        return set;
    }
}
