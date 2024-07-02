package claus.rest.controllers;

import claus.backend.DBObjects.plans.DBElementPlan;
import claus.backend.database.DB;
import claus.backend.domain.domainObjects.plans.ElementPlan;
import claus.backend.domain.domainObjects.plans.Routine;
import claus.backend.domain.plans.ElementPlanDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.UUID;

@RestController
public class PlanController
{
    @GetMapping("api/plans/{teamID}")
    public ElementPlan getPlans(@PathVariable String teamID) throws SQLException
    {
        var con = DB.getConnection();
        var plans = ElementPlanDAO.getByTeam(con, UUID.fromString(teamID));
        DB.freeConnection(con);
        return plans;
    }
    @PostMapping(path = "api/elementplans",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createElementPlan(@RequestBody DBElementPlan plan) throws SQLException
    {
        var con = DB.getConnection();
        ElementPlanDAO.addPlan(con, plan.getName(), plan.getTeamID());
        DB.freeConnection(con);
    }

    @PostMapping(path = "api/elementplans/{team_id}/routines",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addRoutineToPlan(@RequestBody Routine routine) throws SQLException
    {
        var con = DB.getConnection();
        ElementPlanDAO.addRoutine(con, routine);
        DB.freeConnection(con);
    }
}
