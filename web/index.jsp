<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="style/main.css">
    <link rel="stylesheet" type="text/css" href="tableStyle/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="tableStyle/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="tableStyle/vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="tableStyle/vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="tableStyle/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" type="text/css" href="tableStyle/css/util.css">
    <link rel="stylesheet" type="text/css" href="tableStyle/css/main.css">
</head>
<body>

<div class="limiter">
    <h3>Karte</h3>
    <div class="container-table100">
        <div class="wrap-table100">
            <div class="table100">
                <table id="tickets-tbl">
                    <thead>
                    <tr class="table100-head">
                        <th class="column1">One-way</th>
                        <th class="column2">From</th>
                        <th class="column3">To</th>
                        <th class="column4">Depart</th>
                        <th class="column5">Return</th>
                        <th class="column6">Company</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <form id="filter_form">
        Filter by :
        <select id="filter_types" name="filter_types">
            <option value="all">Get all cards</option>
            <option value="one_way_true">Filter cards that are one way</option>
            <option value="one_way_false">Filter cards that are not one way</option>
        </select>
        </label>
        <input type="Submit" value="Filter">
    </form>

    <br><br>
    <form id="add-ticket-form">
        <label>
            One way
            <input type="checkbox" id="one_way_checkbox" name="one_way_checkbox">
        </label>
        <label>
            Travel from
            <input type="text" name="place_from" placeholder="From">
        </label>
        <label>
            Travel to
            <input type="text" name="place_to" placeholder="To">
        </label>
        <label>
            Depart date
            <input type="date" id="depart_date" name="depart_date" value="depart_date">
        </label>
        <label>
            Return date
            <input type="date" id="return_date" name="return_date" value="return_date">
        </label>
        <label>
            Select airlines company
            <select id="avio_kompanije" name="avio_kompanije"></select>
        </label>
        <input type="Submit" value="Submit">
    </form>
</div>


<script src="js/script.js"></script>
</body>
</html>
