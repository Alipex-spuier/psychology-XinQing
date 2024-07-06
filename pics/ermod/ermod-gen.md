```mermaid
erDiagram
    Users {
        INT user_id PK
        VARCHAR user_name
        VARCHAR user_password
        VARCHAR user_email
        VARCHAR user_intro
        DATETIME created_time
    }

    Experts {
        INT ex_id PK
        VARCHAR ex_name
        TEXT ex_bio
        VARCHAR ex_dire
        VARCHAR ex_qualification
    }

    Appointments {
        INT apt_id PK
        INT user_id FK
        INT expert_id FK
        DATETIME apt_time
        VARCHAR apt_status
    }

    ConsultationLogs {
        INT log_id PK
        INT apt_id FK
        TEXT log_content
        DATETIME log_time
    }

    PsychologicalTests {
        INT test_id PK
        VARCHAR test_name
        TEXT test_content
        INT test_score
        TEXT test_answer
    }

    UserTestResults {
        INT res_id PK
        INT user_id FK
        TEXT res_result
        DATETIME res_time
    }

    StressReliefTools {
        INT id PK
        VARCHAR tool_name
        TEXT tool_description
        VARCHAR tool_link
    }

    Articles {
        INT art_id PK
        INT art_author FK
        VARCHAR art_title
        TEXT art_content
        DATETIME art_time
    }

    ArticlesComments {
        INT comments_id PK
        INT art_id FK
        INT user_id FK
        TEXT comments_content
        DATETIME comments_time
    }

    Admins {
        INT admin_id PK
        VARCHAR admin_name
        VARCHAR admin_password
        VARCHAR admin_email
        DATETIME created_time
    }

    UserMessages {
        INT mes_id PK
        INT user_id FK
        INT admin_id FK
        TEXT mes_content
        DATETIME mes_time
    }

    Admins ||--o{ Experts : "manages"
    Users ||--o{ StressReliefTools : "use"
    Users ||--o{ Appointments : "has"
    UserTestResults ||--o{ PsychologicalTests :"from"
    Experts ||--o{ Appointments : "has"
    Appointments ||--o{ ConsultationLogs : "contains"
    Users ||--o{ UserTestResults : "takes"
    Articles ||--o{ ArticlesComments : "contains"
    Users ||--o{ ArticlesComments : "posts"
    Users ||--o{ UserMessages : "receives"
    Admins ||--o{ UserMessages : "sends"
    Experts ||--o{ Articles : "writes"

```
```
<!DOCTYPE html>
<html>
<head>
    <style>
        .er.relationshipLabelBox {
            fill: lightblue; /* 背景颜色 */
            stroke: darkblue; /* 边框颜色 */
            stroke-width: 2px; /* 边框宽度 */
        }
    </style>
</head>
<body>
    <div class="mermaid">
        erDiagram
        CUSTOMER ||--o{ ORDER : places
        ORDER ||--|{ LINE-ITEM : contains
        CUSTOMER {
            string name
            string custNumber
            string sector
        }
        ORDER {
            int orderNumber
            string deliveryAddress
            string orderDate
        }
        LINE-ITEM {
            string productCode
            int quantity
            float pricePerUnit
        }
    </div>
    <script type="module">
        import mermaid from 'https://cdn.jsdelivr.net/npm/mermaid@9/dist/mermaid.esm.min.mjs';
        mermaid.initialize({ startOnLoad: true });

        // 等待 Mermaid 渲染完成后执行代码
        document.addEventListener('DOMContentLoaded', function () {
            document.querySelectorAll('.er.relationshipLabelBox').forEach(function (label) {
                // 获取原来矩形的位置和大小属性
                const x = parseFloat(label.getAttribute('x'));
                const y = parseFloat(label.getAttribute('y'));
                const width = parseFloat(label.getAttribute('width'));
                const height = parseFloat(label.getAttribute('height'));

                // 计算菱形的四个顶点
                const points = [
                    [x + width / 2, y], // 顶点
                    [x + width, y + height / 2], // 右顶点
                    [x + width / 2, y + height], // 底部顶点
                    [x, y + height / 2] // 左顶点
                ].map(point => point.join(',')).join(' ');

                // 创建一个新的菱形元素
                const diamond = document.createElementNS("http://www.w3.org/2000/svg", "polygon");
                diamond.setAttribute('points', points);
                diamond.setAttribute('fill', label.getAttribute('fill'));
                diamond.setAttribute('stroke', label.getAttribute('stroke'));
                diamond.setAttribute('stroke-width', label.getAttribute('stroke-width'));
                diamond.setAttribute('class', 'er.relationshipLabelBox');

                // 将新的菱形元素插入到原来的矩形位置
                label.parentNode.insertBefore(diamond, label);

                // 移除原来的矩形元素
                label.remove();
            });
        });
    </script>
</body>
</html>
```
```
 .rhombus{
            width: 100px;
            height: 100px;
            transform: rotateZ(45deg)skew(30deg,30deg);
            background: blue;
        }
```
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <div class="mermaid">
        erDiagram
        CUSTOMER ||--o{ ORDER : places
        ORDER ||--|{ LINE-ITEM : contains
        CUSTOMER {
            string name
            string custNumber
            string sector
        }
        ORDER {
            int orderNumber
            string deliveryAddress
            string orderDate
        }
        LINE-ITEM {
            string productCode
            int quantity
            float pricePerUnit
        }
    </div>
    
</body>
</html>

