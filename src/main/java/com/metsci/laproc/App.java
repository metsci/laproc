package com.metsci.laproc;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.metsci.laproc.application.Application;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.data.DataPointImpl;
import com.metsci.laproc.data.TagHeader;

/**
 * Hello world!
 *
 */
public class App {
    private static boolean useFirstDataSet = true;

	public static void main( String[] args )
    {
		List<ClassifierDataSet> dataSetList = new ArrayList<ClassifierDataSet>();
		List<TagHeader> tagHeaders = new ArrayList<TagHeader>();
        if(useFirstDataSet) {
            importData(dataSetList, tagHeaders);
        } else {
            importData2(dataSetList, tagHeaders);
        }

        Application application = new Application(dataSetList, tagHeaders);
        application.run();
    }

    private static void importData(List<ClassifierDataSet> dataSetList, List<TagHeader> tagHeaders) {
        try {
            CSVReader reader = new CSVReader("..\\laproc\\test-data\\dataset1.csv");
            String[] line = reader.getLine();

            TagHeader tag0 = new TagHeader("classifier");
            TagHeader tag1 = new TagHeader(line[0]);
            TagHeader tag2 = new TagHeader(line[1]);
            TagHeader tag3 = new TagHeader(line[2]);

            tagHeaders.add(tag0);
            tagHeaders.add(tag1);
            tagHeaders.add(tag2);
            tagHeaders.add(tag3);

            while(true) {
                line = reader.getLine();
                if(line == null)
                    break;
                DataPoint point;
                if(line[3].equals("1"))
                    point = new DataPointImpl(true, Double.parseDouble(line[4]));
                else
                    point = new DataPointImpl(false, Double.parseDouble(line[4]));
                boolean found = false;
                for(ClassifierDataSet set : dataSetList){
                    List<List<String>> tags = set.getTags();
                    if(tags.get(0).contains("classifier0") &&
                            tags.get(0).contains(line[0]) &&
                            tags.get(0).contains(line[1]) &&
                            tags.get(0).contains(line[2])){
                        set.add(point);
                        found = true;
                        break;
                    }
                }
                if(!found){
                    ArrayList<List<String>> tags = new ArrayList<List<String>>();
                    ArrayList<String> tagSet = new ArrayList<String>();
                    tagSet.add("classifier0");
                    tagSet.add(line[0]);
                    tagSet.add(line[1]);
                    tagSet.add(line[2]);
                    tags.add(tagSet);
                    ClassifierDataSet newSet = new ClassifierDataSet(tags, "meaningless");
                    newSet.add(point);
                    dataSetList.add(newSet);
                }

                //Create Second Classifer With Random Values for Points
                DataPoint point1;
                if(line[3].equals("1"))
                    point1 = new DataPointImpl(true, Math.random());
                else
                    point1 = new DataPointImpl(false, Math.random());
                found = false;
                for(ClassifierDataSet set : dataSetList){
                    List<List<String>> tags = set.getTags();
                    if(tags.get(0).contains("classifier1") &&
                            tags.get(0).contains(line[0]) &&
                            tags.get(0).contains(line[1]) &&
                            tags.get(0).contains(line[2])){
                        set.add(point1);
                        found = true;
                        break;
                    }
                }
                if(!found){
                    ArrayList<List<String>> tags = new ArrayList<List<String>>();
                    ArrayList<String> tagSet = new ArrayList<String>();
                    tagSet.add("classifier1");
                    tagSet.add(line[0]);
                    tagSet.add(line[1]);
                    tagSet.add(line[2]);
                    tags.add(tagSet);
                    ClassifierDataSet newSet = new ClassifierDataSet(tags, "meaningless");
                    newSet.add(point1);
                    dataSetList.add(newSet);
                }
            }

            for(ClassifierDataSet dataSet : dataSetList){
                List<List<String>> tags = dataSet.getTags();
                List<String> tagSet = tags.get(0);
                for(int i = 0; i < tagSet.size(); i++){
                    if(!tagHeaders.get(i).getTags().contains(tagSet.get(i))){
                        tagHeaders.get(i).addTag(tagSet.get(i));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void importData2(List<ClassifierDataSet> dataSetList, List<TagHeader> tagHeaders) {
        try {
            CSVReader reader = new CSVReader("..\\laproc\\test-data\\dataset2.csv");
            String[] firstLine = reader.getLine();

            TagHeader tag0 = new TagHeader("Feature");
            TagHeader tag1 = new TagHeader("Exercise");

            tagHeaders.add(tag0);
            tagHeaders.add(tag1);

            while(true) {
                String[] line = reader.getLine();
                if (line == null)
                    break;
                for (int i = 0; i < line.length - 2; i++) {
                    DataPoint point;
                    if (line[line.length-2].equals("1"))
                        point = new DataPointImpl(true, Double.parseDouble(line[i]));
                    else
                        point = new DataPointImpl(false, Double.parseDouble(line[i]));
                    boolean found = false;
                    for (ClassifierDataSet set : dataSetList) {
                        List<List<String>> tags = set.getTags();
                        if (tags.get(0).contains(firstLine[i]) &&
                                tags.get(0).contains(line[line.length-1])) {
                            set.add(point);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        ArrayList<List<String>> tags = new ArrayList<List<String>>();
                        ArrayList<String> tagSet = new ArrayList<String>();
                        tagSet.add(firstLine[i]);
                        tagSet.add(line[line.length-1]);
                        tags.add(tagSet);
                        ClassifierDataSet newSet = new ClassifierDataSet(tags, "meaningless");
                        newSet.add(point);
                        dataSetList.add(newSet);
                    }
                }
            }
            for(ClassifierDataSet dataSet : dataSetList){
                List<List<String>> tags = dataSet.getTags();
                List<String> tagSet = tags.get(0);
                for(int i = 0; i < tagSet.size(); i++){
                    if(!tagHeaders.get(i).getTags().contains(tagSet.get(i))){
                        tagHeaders.get(i).addTag(tagSet.get(i));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
