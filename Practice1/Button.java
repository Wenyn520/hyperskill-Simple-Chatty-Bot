//demonstrating local class and anonymous class
public class Button {
    private String title;
    private OnClickListener onClickListener;

    public Button(String title) {
        this.title = title;
    }

    //getter
    public String getTitle() {
        return title;
    }

    //setter
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onClick(){
        onClickListener.onClick(title);
    }

    //interface
    public interface OnClickListener{
        public void onClick(String title);
    }
}
