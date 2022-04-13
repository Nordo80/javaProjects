package gol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    public Map<Integer, List<Integer>> map = new HashMap<>();
    public void markAlive(int x, int y) {
        if(!map.containsKey(x)) {
            map.put(x, new ArrayList<>());
        }
        map.get(x).add(y);
        }

    public boolean isAlive(int x, int y) {
        if(map.isEmpty()){
            return false;
        }
        for(Map.Entry<Integer, List<Integer>> pair : map.entrySet()){
            for(int a: pair.getValue()){
                if(a == y && pair.getKey().equals(x)){
                    return true;
                }
            }
        }return false;
    }

    public void toggle(int x, int y) {
        if(isAlive(x,y)){
            remove(x,y);
        }else{
            markAlive(x, y);
        }
    }
    public void remove(int x, int y){
        List<Integer> newOneY = map.get(x);
        map.remove(x);
        for(Integer yInteger : newOneY){
            if(yInteger!=y){
                markAlive(x, yInteger);
            }
        }
    }

    public Integer getNeighbourCount(int x, int y) {
        int counter;
        if(isAlive(x,y)){
            counter = -1;
        }else{
            counter = 0;
        }

        for(int yPoint = -1; yPoint <= 1; yPoint++){
            for(int xPoint = - 1; xPoint <= 1; xPoint++){
                if(isAlive(x + xPoint , y + yPoint)){
                    counter += 1;
                }
            }
        }
        return counter;
    }
    public void nextFrame() {
        int columns;
        int raws;
        Map<Integer, List<Integer>> emptyMap = new HashMap<>();
        for (columns = 0; columns <= 19; columns++) {
            for (raws = 0; raws <= 19; raws++) {
                if (nextState(isAlive(columns, raws), getNeighbourCount(columns, raws))) {
                    if (!emptyMap.containsKey(columns)) {
                        emptyMap.put(columns, new ArrayList<>());
                    }
                    emptyMap.get(columns).add(raws);
                }
            }
        }
        map = emptyMap;
    }

    public void clear() {
        map.clear();
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        return (!isLiving || 2 <= neighborCount) && neighborCount <= 3 && (isLiving || neighborCount == 3);
    }
}
