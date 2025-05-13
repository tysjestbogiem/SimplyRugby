package simpleRugby.controler;

import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import simpleRugby.model.PlayerPerformanceDAO;
import simpleRugby.model.Skill;
import simpleRugby.model.SkillPerformanceGraphDAO;
import simpleRugby.view.SkillPerformanceGraph;


/**
 * This controller helps create a dataset for drawing skill performance charts.
 * It takes data from the DAO and prepares it in a way the chart can understand.
 */
public class SkillPerformanceGraphController {

    // Handles data access for skill performance statistics
    private SkillPerformanceGraphDAO mySkillPerformanceGraphDAO;

    // Responsible for building actual chart view
    private SkillPerformanceGraph mySkillPerformanceGraph;

    /**
     * Constructor connects the DAO and chart view so they can work together.
     */
    public SkillPerformanceGraphController(SkillPerformanceGraphDAO mySkillPerformanceGraphDAO, SkillPerformanceGraph mySkillPerformanceGraph) {
        this.mySkillPerformanceGraphDAO = mySkillPerformanceGraphDAO;
        this.mySkillPerformanceGraph = mySkillPerformanceGraph;
    }

    /**
     * Takes a list of skill records and turns it into a format the chart can use.
     * Each entry shows the level of a skill on a particular date.
     */
    public CategoryDataset createDataset(List<Skill> skills) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Skill skill : skills) {
            String skillName = skill.getSkillName(); // what skill 
            String trainingDate = skill.getTrainingDateChanged(); // when it was rated
            int level = (int) Math.round(skill.getLevel()); // skill level as integer

            // Only include data if both the name and date are valid
            if (skillName != null && trainingDate != null) {
                dataset.addValue(level, skillName, trainingDate);
            }
        }

        return dataset;
    }
}

     