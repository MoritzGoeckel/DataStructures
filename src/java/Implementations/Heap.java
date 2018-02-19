package Implementations;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Heap {
    private ArrayList<Integer> array = new ArrayList<>();

    public void insert(int number){
        array.add(number);

        int currentIndex = array.size() - 1;

        //Heapyify
        while (currentIndex != 0) {
            int parentIndex = getParentIndex(currentIndex);
            if (array.get(parentIndex) < array.get(currentIndex)) {
                int oldParent = array.get(parentIndex);
                array.set(parentIndex, array.get(currentIndex));
                array.set(currentIndex, oldParent);
            }
            else
                break;

            currentIndex = parentIndex;
        }
    }

    private int getParentIndex(int index){
        if(index % 2 == 0)
            return (index / 2) - 1;
        else //if(index % 2 == 1)
            return (index / 2);
    }

    public int getMax(){
        return array.get(0);
    }

    public int popMax(){
        int index = 0;

        //Replace root with leaf
        int max = array.get(index);
        array.set(index, array.get(array.size() - 1));
        array.remove(array.size() - 1);

        //Repair
        while (index < array.size()){
            int childOne = index * 2 + 1;
            int childTwo = index * 2 + 2;

            int targetIndex = childOne;

            if (childTwo < array.size() && array.get(childOne) < array.get(childTwo))
                targetIndex = childTwo;

            if (targetIndex < array.size() && array.get(index) < array.get(targetIndex)) {
                int tmp = array.get(index);
                array.set(index, array.get(targetIndex));
                array.set(targetIndex, tmp);

                index = targetIndex;
            }
            else
                break;
        }

        return max;
    }

    public int getSize(){
        return array.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(Integer i : array)
            sb.append(i).append(", ");

        if(sb.length() > 1)
            sb.delete(sb.length() - 2, sb.length());

        sb.append("]");
        return sb.toString();
    }
}
