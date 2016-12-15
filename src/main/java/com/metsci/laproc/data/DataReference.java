package com.metsci.laproc.data;

import com.metsci.laproc.plotting.Graph;

import java.util.Observable;
import java.util.Set;

/**
 * Created by porterjc on 12/14/2016.
 */
public class DataReference extends Observable{
    private Graph graph;
    private Set<ClassifierDataSet> evalSets;
}
