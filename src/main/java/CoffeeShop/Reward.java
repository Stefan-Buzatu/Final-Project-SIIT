package CoffeeShop;

import java.util.UUID;

public class Reward {
    private UUID rewardID;
    private String reward;
    private int numberOfEntries;

    public Reward(UUID rewardID, String reward,int numberOfEntries) {
        this.rewardID = rewardID;
        this.reward = reward;
        this.numberOfEntries=numberOfEntries;
    }

    public UUID getRewardID() {
        return rewardID;
    }

    public void setRewardID(UUID rewardID) {
        this.rewardID = rewardID;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }


    @Override
    public String toString() {
        return "Reward{" +
                "rewardID=" + rewardID +
                ", reward='" + reward + '\'' +
                ", numberOfEntries=" + numberOfEntries +
                '}';
    }
}
