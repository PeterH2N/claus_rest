package claus.rest.controllers;

import claus.backend.DBObjects.elements.DBCategory;
import claus.backend.DBObjects.elements.DBElement;
import claus.backend.DBObjects.elements.DBElementRequirement;
import claus.backend.DBObjects.teams.DBTeam;
import claus.backend.DBObjects.teams.DBUser;
import claus.backend.database.DB;
import claus.backend.domain.elements.CategoryDAO;
import claus.backend.domain.elements.ElementDAO;
import claus.backend.domain.elements.ElementRequirementDao;
import claus.backend.domain.teams.TeamDAO;
import claus.backend.domain.teams.UserDAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class ElementController
{
    @GetMapping("api/{cop}/element/{code}")
    DBElement getOne(@PathVariable String cop, @PathVariable String code) throws SQLException
    {
        var con = DB.getConnection();
        var elem = ElementDAO.get(con, cop, code);
        DB.freeConnection(con);
        return elem;
    }

    @GetMapping("api/{cop}/elements")
    List<DBElement> getAll(@PathVariable String cop) throws SQLException
    {
        var con = DB.getConnection();
        var elems = ElementDAO.getAllByCoP(con, cop);
        DB.freeConnection(con);
        return elems;
    }

    @GetMapping("api/{cop}/categories/parents")
    List<DBCategory> getParents(@PathVariable String cop) throws SQLException
    {
        var con = DB.getConnection();
        var cats = CategoryDAO.getChildren(con, cop, "");
        DB.freeConnection(con);
        return cats;
    }

    @GetMapping("api/{cop}/categories/{parent}")
    List<DBCategory> getChildren(@PathVariable String cop, @PathVariable String parent) throws SQLException
    {
        var con = DB.getConnection();
        var cats = CategoryDAO.getChildren(con, cop, parent);
        DB.freeConnection(con);
        return cats;
    }

    @GetMapping("api/{cop}/elements/{catCode}")
    List<DBElement> getByCategory(@PathVariable String cop, @PathVariable String catCode) throws SQLException
    {
        var con = DB.getConnection();
        var elems = ElementDAO.getBySpecificCategory(con, cop, catCode);
        DB.freeConnection(con);
        return elems;
    }

    @GetMapping("api/{cop}/requirements")
    List<DBElementRequirement> getReqs(@PathVariable String cop) throws SQLException
    {
        var con = DB.getConnection();
        var reqs = ElementRequirementDao.getByCoP(con, cop);
        DB.freeConnection(con);
        return reqs;
    }

    @GetMapping("api/users/{email}")
    DBUser getUser(@PathVariable String email) throws SQLException
    {
        var con = DB.getConnection();
        var user = UserDAO.getUser(con, email);
        DB.freeConnection(con);
        return user;
    }

    @PostMapping(path = "api//users",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody DBUser newUser) throws SQLException
    {
        var con = DB.getConnection();
        UserDAO.addUser(con, newUser);
        DB.freeConnection(con);
    }

    @GetMapping("api/teams/coach/{userID}")
    List<DBTeam> getTeamsC(@PathVariable String userID) throws SQLException
    {
        var con = DB.getConnection();
        var teams = TeamDAO.getTeamsFromCoach(con, UUID.fromString(userID));
        DB.freeConnection(con);
        return teams;
    }

    @GetMapping("api/teams/gymnast/{userID}")
    List<DBTeam> getTeamsG(@PathVariable String userID) throws SQLException
    {
        var con = DB.getConnection();
        var teams = TeamDAO.getTeamsFromGymnast(con, UUID.fromString(userID));
        DB.freeConnection(con);
        return teams;
    }

    @GetMapping("api/teams/{teamID}")
    DBTeam getTeam(@PathVariable String teamID) throws SQLException
    {
        var con = DB.getConnection();
        var team = TeamDAO.getDBTeam(con, UUID.fromString(teamID));
        DB.freeConnection(con);
        return team;
    }



}
