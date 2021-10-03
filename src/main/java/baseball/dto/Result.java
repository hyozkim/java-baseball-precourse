package baseball.dto;

public class Result {
    private Progress progress;
    private String description;

    public Result(Progress progress, String description) {
        this.progress = progress;
        this.description = description;
    }

    static public class Builder {
        private Progress progress;
        private String description;

        public Builder() {}

        public Builder(Result result) {
            this.progress = result.progress;
            this.description = result.description;
        }

        public Builder progress(Progress progress) {
            this.progress = progress;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Result build() {
            return new Result(progress, description);
        }
    }

    public Progress getProgress() {
        return progress;
    }

    public String getDescription() {
        return description;
    }
}
