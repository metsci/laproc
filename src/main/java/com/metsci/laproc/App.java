package com.metsci.laproc;

import java.io.IOException;
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
	public static void main( String[] args )
    {
		List<ClassifierDataSet> dataSetList = new ArrayList<ClassifierDataSet>();
		List<TagHeader> tagHeaders = new ArrayList<TagHeader>();
        importData(dataSetList, tagHeaders);

        Application application = new Application(dataSetList, tagHeaders);
        application.run();
    }

    private static void importData(List<ClassifierDataSet> dataSetList, List<TagHeader> tagHeaders) {
        try {
            CSVReader reader = new CSVReader("..\\laproc\\test-data\\dataset1.csv");
            String[] line = reader.getLine();
            TagHeader tag1 = new TagHeader(line[0]);
            TagHeader tag2 = new TagHeader(line[1]);
            TagHeader tag3 = new TagHeader(line[2]);
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
                	Collection<String> tags = set.getTags();
                	if(tags.contains(line[0]) && tags.contains(line[1]) && tags.contains(line[2])){
                		set.add(point);
                		found = true;
                		break;
                	}
                }
                if(!found){
                	ArrayList<String> tags = new ArrayList<String>();
                	tags.add(line[0]);
                	tags.add(line[1]);
                	tags.add(line[2]);
                	ClassifierDataSet newSet = new ClassifierDataSet(tags, "meaningless");
                	newSet.add(point);
                	dataSetList.add(newSet);
                }
            }
            
            for(ClassifierDataSet dataSet : dataSetList){
            	List<String> tags = (List<String>) dataSet.getTags();
            	for(int i = 0; i < tags.size(); i++){
            		if(!tagHeaders.get(i).getTags().contains(tags.get(i))){
            			tagHeaders.get(i).addTag(tags.get(i));
            		}
            	}
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
