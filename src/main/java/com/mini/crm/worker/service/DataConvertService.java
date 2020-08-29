package com.mini.crm.worker.service;

import com.mini.crm.worker.config.AppConfiguration;
import com.mini.crm.worker.controller.data.Response;
import com.mini.crm.worker.controller.data.ResponseStatus;
import com.mini.crm.worker.model.Employee;
import com.mini.crm.worker.model.EmployeeOf;
import com.mini.crm.worker.repo.CompanyRepo;
import com.mini.crm.worker.repo.EmployeeOfRepo;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.StreamSupport;

@Service
public class DataConvertService {

    @Autowired
    private AppConfiguration appConfiguration;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private EmployeeOfRepo employeeOfRepo;

    public void executeGraphByCompany(String companyName) throws IOException {
        var company = companyRepo.findByName(companyName);
        if (company != null) {
            File imgFile = new File(appConfiguration.getPath());
            imgFile.createNewFile();

            //Init graph
            DefaultDirectedGraph<String, DefaultEdge> g =
                    new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
            g.addVertex(companyName);

            //Load data to graph
            StreamSupport
                    .stream(employeeOfRepo.findAll().spliterator(), false)
                    .forEach(employeeOf -> {
                        var eName = employeeOf.getEmployee().getName();
                        g.addVertex(eName);
                        g.addEdge(eName, companyName);
                    });

            //Init view of graph
            JGraphXAdapter<String, DefaultEdge> graphAdapter =
                    new JGraphXAdapter<String, DefaultEdge>(g);
            mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
            layout.execute(graphAdapter.getDefaultParent());

            //Save graph view to image
            BufferedImage image =
                    mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
            File img2File = new File(appConfiguration.getPath());
            ImageIO.write(image, "PNG", img2File);
        }
    }
}
