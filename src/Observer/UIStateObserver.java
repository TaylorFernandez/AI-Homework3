package Observer;
import java.util.List;
import java.util.ArrayList;

public class UIStateObserver {
    List<Observable> observableList = new ArrayList<Observable>();
    private static UIStateObserver uiObserver;

    private UIStateObserver(){}

    public static UIStateObserver getSingleton(){
        if(uiObserver == null){
            uiObserver = new UIStateObserver();
        }
        return uiObserver;
    }

    public void addObserver(Observable o){
        observableList.add(o);
    }

    public void removeObserver(Observable o) {
        observableList.remove(o);
    }

    public void notifyAllObservers(){
        for(int i = 0; i < observableList.size(); i++){
            observableList.get(i).update();
        }
    }
}
