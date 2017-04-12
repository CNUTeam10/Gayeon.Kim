package univ.lecture.riotapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team10 {
    private int teamId;
    private long now;
    private double result;
    
    public void setTeamId(int teamId)
    {
       this.teamId = teamId;
    }
    
    public void setNow(long now)
    {
       this.now = now;
    }
    
    public void setResult(double result)
    {
       this.result = result;
    }
}