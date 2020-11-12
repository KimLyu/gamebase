package maploader;

import java.io.IOException;
import java.util.ArrayList;
import gametest7th.gameobj.GameObject;
import java.awt.Graphics;

public class MapLoader {

    private final ArrayList<int[][]> mapArr;
    private final ArrayList<String[]> txtArr;

    public MapLoader(String MapPath, String txtPath) throws IOException { //txt檔名  bmp檔名
        mapArr = new ReadBmp().readBmp(MapPath);
        txtArr = new ReadFile().readFile(txtPath);
    }

    public static interface CompareClass {

        public GameObject compareClassName(String gameObject, String name, MapInfo mapInfo, int MapObjectSize);
    }

    public ArrayList<GameObject> creatObjectArray(String gameObject, int mapObjectSize, ArrayList<MapInfo> mapInfo, CompareClass compare) {
        ArrayList<GameObject> tmp = new ArrayList();
        mapInfo.forEach((e) -> {
            GameObject tmpObject = compare.compareClassName(gameObject, e.getName(), e, mapObjectSize);
            if (tmpObject != null) {
                tmp.add(tmpObject);
            }
        });
        return tmp;
    }

    public ArrayList<MapInfo> combineInfo() {  //整合需要資料   類名  x座標  y座標 尺寸(e.g. 1 * 1)
        ArrayList<MapInfo> result = new ArrayList();
        for (int i = 0; i < mapArr.size(); i++) {
            for (int j = 0; j < txtArr.size(); j++) {
                if (mapArr.get(i)[1][0] == Integer.parseInt(txtArr.get(j)[1])) {
                    MapInfo tmp = new MapInfo(txtArr.get(j)[0],
                            mapArr.get(i)[0][0],
                            mapArr.get(i)[0][1],
                            Integer.parseInt(txtArr.get(j)[2]),
                            Integer.parseInt(txtArr.get(j)[3]));
                    result.add(tmp);
                }
            }
        }
        return result;
    }

//    public ArrayList<String[]> combineInfo() {  //整合需要資料   類名  x座標  y座標 尺寸(e.g. 1 * 1)
//        ArrayList<String[]> result = new ArrayList();
//        for (int i = 0; i < mapArr.size(); i++) {
//            for (int j = 0; j < txtArr.size(); j++) {
//                if (mapArr.get(i)[1][0] == Integer.parseInt(txtArr.get(j)[1])) {
//                    String[] tmp = new String[5];
//                    tmp[0] = txtArr.get(j)[0];
//                    tmp[1] = String.valueOf(mapArr.get(i)[0][0]);
//                    tmp[2] = String.valueOf(mapArr.get(i)[0][1]);
//                    tmp[3] = txtArr.get(j)[2];
//                    tmp[4] = txtArr.get(j)[3];
//                    result.add(tmp);
//                }
//            }
//        }
//        return result;
//    }
    public ArrayList<int[][]> getMapArr() {
        return mapArr;
    }

    public ArrayList getTxtArr() {
        return txtArr;
    }

}