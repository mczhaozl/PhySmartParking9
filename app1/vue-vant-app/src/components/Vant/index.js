import Vue from "vue";
import {
    Button,
    List,
    Cell,
    CellGroup,
    Icon,
    Image,
    RadioGroup,
    Radio,
    CheckboxGroup,
    Checkbox,
    NavBar,
    Field,
    Picker,
    Collapse,
    CollapseItem,
    Popup,
    Uploader,
    Progress,
    DatetimePicker,
    Tab,
    Tabs,
    Overlay,
    Slider,
    Stepper,
    Toast,
    Circle,
    Step,
    Steps,
    IndexBar,
    IndexAnchor,
    Sticky,
    PullRefresh,
    Col,
    Row,
    Form,
    Calendar,
    Switch,
    NumberKeyboard,
    Search,
    Dialog,
    DropdownMenu,
    DropdownItem,
    ImagePreview
} from 'vant' // 引入vant组件


[
    Button,
    List,
    Cell,
    CellGroup,
    Icon,
    Image,
    RadioGroup,
    Radio,
    NavBar,
    Field,
    Picker,
    Collapse,
    CollapseItem,
    RadioGroup,
    Radio,
    CheckboxGroup,
    Checkbox,
    Popup,
    Uploader,
    Progress,
    DatetimePicker,
    Tab,
    Tabs,
    Overlay,
    Slider,
    Stepper,
    Toast,
    Circle,
    Step,
    Steps,
    IndexBar,
    IndexAnchor,
    Sticky,
    PullRefresh,
    Col,
    Row,
    Form,
    Calendar,
    Switch,
    NumberKeyboard,
    Search,
    Dialog,
    DropdownMenu,
    DropdownItem,
    ImagePreview 
].forEach(v => {
    Vue.use(v)
})